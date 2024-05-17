package register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.task5.response.Response;
import ru.spring.task5.response.ResponseProductRegisterOK;
import ru.spring.task5.modelDB.*;
import ru.spring.task5.response.ResponseError;
import ru.spring.task5.response.RestJsonError;

@Service
@Transactional
public class ProductRegisterCreate {
    private TppProductRegisterRepo tppProductRegisterRepo;
    private TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo;
    private AccountRepo accountRepo;

    @Autowired
    public void setTppProductRegister(TppProductRegisterRepo tppProductRegisterRepo) {
        this.tppProductRegisterRepo = tppProductRegisterRepo;
    }

    @Autowired
    public void setTppRefProductRegisterTypeRepo(TppRefProductRegisterTypeRepo tppRefProductRegisterTypeRepo) {
        this.tppRefProductRegisterTypeRepo = tppRefProductRegisterTypeRepo;
    }

    @Autowired
    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Response process(ProductRegisterJson js) {
        if (tppProductRegisterRepo.existsProductType(js.instanceId(), js.registryTypeCode())) {
            return new ResponseError(RestJsonError.EXISTS, "Параметр registryTypeCode тип регистра <" + js.registryTypeCode() + "> уже существует для ЭП с ИД <" + js.instanceId() + ">.");
        }

        var optRegisterType = tppRefProductRegisterTypeRepo.findByValue(js.registryTypeCode());
        final TppRefProductRegisterType RegisterType;
        if (optRegisterType.isPresent()) {
            RegisterType = optRegisterType.get();
        } else {
            return new ResponseError(RestJsonError.NOT_FOUND, "Код Продукта <" + js.registryTypeCode() + "> не найден в Каталоге продуктов <TppRefProductRegisterType> для данного типа Регистра");
        }
        final var optAccount = accountRepo.getAccountByPool(js.branchCode(), js.currencyCode(), js.mdmCode(), js.priorityCode(), js.registryTypeCode());
        final Account account;
        if (optAccount.isPresent()) {
            account = optAccount.get();
        } else {
            return new ResponseError(RestJsonError.NOT_FOUND, "Не найден пул счетов");
        }

        final var tppProductRegisterDTO = new TppProductRegister();
        tppProductRegisterDTO.setState(TppProductRegisterState.S1);
        tppProductRegisterDTO.setProductId(js.instanceId());

        tppProductRegisterDTO.setAccount(account.getId());
        tppProductRegisterDTO.setAccountNumber(account.getAccountNumber());

        tppProductRegisterDTO.setTppRefProductRegisterType(RegisterType);
        tppProductRegisterDTO.setCurrencyCode(js.currencyCode());
        tppProductRegisterRepo.save(tppProductRegisterDTO);

        return new ResponseProductRegisterOK(tppProductRegisterDTO.getId());
    }
}

