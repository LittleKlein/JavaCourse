package product;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.spring.task5.TransactionSimple;
import ru.spring.task5.register.ProductRegisterCreate;
import ru.spring.task5.register.ProductRegisterJson;
import ru.spring.task5.response.*;
import ru.spring.task5.modelDB.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCreate {
    private static final String typeAccount = "Клиентский";
    private final TppProductRepo tppProductRepo;
    private final TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;
    private final TppProductRegisterRepo tppProductRegisterRepo;
    private final AgreementCheck agreementCheck;

    private final ProductRegisterCreate productRegisterCreate;

    private final TransactionSimple transactionSimple;

    @Getter
    private Response response;

    public ProductCreate(TppProductRepo tppProductRepo, TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo, TppProductRegisterRepo tppProductRegisterRepo, AgreementCheck agreementCheck, ProductRegisterCreate productRegisterCreate, TransactionSimple transactionSimple) {
        this.tppProductRepo = tppProductRepo;
        this.tppRefProductRegisterTypeRepo = tppRefProductRegisterTypeRepo;
        this.tppProductRegisterRepo = tppProductRegisterRepo;
        this.agreementCheck = agreementCheck;
        this.productRegisterCreate = productRegisterCreate;
        this.transactionSimple = transactionSimple;
    }

    public void process(ProductKeepJson js) {
        transactionSimple.begin("createProduct");
        try {
            processInTransaction(js);
        } catch (Exception e) {
            transactionSimple.rollback();
            throw new RuntimeException(e);
        }
    }

    private void processInTransaction(ProductKeepJson js) {
        final var tppProductOptional = tppProductRepo.findFirstByNumber(js.contractNumber());
        if (tppProductOptional.isPresent()) {
            response = new ResponseError(RestJsonError.EXISTS
                    , "Параметр ContractNumber № договора <" + js.contractNumber() + "> уже существует для ЭП с ИД  <" + tppProductOptional.get().getId() + ">.");
            return;
        }

        if (js.instanceArrangement() != null) {
            final var check = agreementCheck.check(js.instanceArrangement());
            if (check.isPresent()) {
                response = new ResponseErrorList(check.get());
                return;
            }
        }

        final var productRegisterType = tppRefProductRegisterTypeRepo.findAllProductTypeClient(js.productCode(), typeAccount);
        if (productRegisterType.size() == 0) {
            response = new ResponseError(RestJsonError.NOT_FOUND
                    , "КодПродукта <" + js.productCode() + "> не найдено в Каталоге продуктов tpp_ref_product_class");
            return;
        }

        final var product = new TppProduct();
        product.setNumber(js.contractNumber());
        tppProductRegisterRepo.findById(js.registerType()).ifPresent(registerType -> product.setTppProductRegister(registerType));
        product.setDateOfConclusion(js.contractDate());
        product.setPriority(js.priority());
        product.setType(TppProductType.getEnum(js.productType()));
        product.setState(TppProductState.S1);
        product.setClientId(Long.valueOf(js.mdmCode()));
        tppProductRepo.save(product);

        final List<String> registers = new ArrayList<>();
        for (var l : productRegisterType) {
            var productRegisterJson = new ProductRegisterJson(
                    product.getId()
                    , l.getValue()
                    , typeAccount
                    , js.isoCurrencyCode()
                    , js.branchCode()
                    , js.urgencyCode()
                    , js.mdmCode()
            );
            final var productRegisterResponse = productRegisterCreate.process(productRegisterJson);
            if (productRegisterResponse instanceof ResponseError err) {
                transactionSimple.rollback();
                response = err;
                return;
            } else if (productRegisterResponse instanceof ResponseProductRegisterOK rOk) {
                registers.add(rOk.data().accountId().toString());
            }
        }

        response = new ResponseProductOK(product.getId(), registers, new ArrayList<>());
        transactionSimple.commit();
    }
}


