package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.task5.modelDB.TppProductType;
import ru.spring.task5.response.*;
import ru.spring.task5.modelDB.Agreement;
import ru.spring.task5.modelDB.AgreementRepo;
import ru.spring.task5.modelDB.TppProductRepo;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgreementCreate {
    private final AgreementRepo agreementRepo;

    private final TppProductRepo tppProductRepo;

    private final AgreementCheck agreementCheck;

    @Autowired
    public AgreementCreate(AgreementRepo agreementRepo, TppProductRepo tppProductRepo, AgreementCheck agreementCheck) {
        this.agreementRepo = agreementRepo;
        this.tppProductRepo = tppProductRepo;
        this.agreementCheck = agreementCheck;
    }

    public Response process(ProductKeepJson js){
        var productOptional = tppProductRepo.findById(js.instanceId());
        if (!productOptional.isPresent()){
            new ResponseError(RestJsonError.NOT_FOUND
                            ,"Экземпляр продукта с параметром instanceId <"+js.instanceId()+"> не найден.");
        }
        final var product = productOptional.get();

        if (js.instanceArrangement() != null) {
            final var check = agreementCheck.check(js.instanceArrangement());
            if (check.isPresent()) {
                return new ResponseErrorList(check.get());
            }
        }


        final var agreementsDTO = js.instanceArrangement().stream()
                .map(agr -> {
                    var agreement = new Agreement();
                    agreement.setTppProduct(product);
                    agreement.setNumber(agr.number());
                    agreement.setArrangementType(TppProductType.getEnum(agr.arrangementType()));
                    agreement.setGeneralAgreementId(agr.generalAgreementId());
                    agreement.setSupplementary_agreement_id(agr.supplementaryAgreementId());
                    agreement.setCancelDate(agr.cancelDate());
                    agreement.setClosingDate(agr.closingDate());
                    agreement.setCancellationReason(agr.cancellationReason());
                    agreement.setCoefficient(agr.coefficient());
                    agreement.setCoefficientAction(agr.coefficientAction());
                    agreement.setInterestCalculationDate(agr.interestCalculationDate());
                    agreement.setInterestRate(agr.interestRate());
                    agreement.setShedulerJobId(agr.shedulerJobId());
                    agreement.setOpeningDate(agr.openingDate());
                    agreement.setMaximalInterestRate(agr.maximaInterestRate());
                    agreement.setMaximalInterestRateCoefficient(agr.maximaInterestRateCoefficient());
                    agreement.setMaximalInterestRateCoefficientAction(agr.maximaInterestRateCoefficientAction());
                    agreement.setMinimumInterestRate(agr.minimumInterestRate());
                    agreement.setMinimumInterestRateCoefficient(agr.minimumInterestRateCoefficient());
                    agreement.setMinimumInterestRateCoefficientAction(agr.minimumInterestRateCoefficientAction());
                    return agreement;
                })
                .collect(Collectors.toList());
        agreementRepo.saveAll(agreementsDTO);

        return new ResponseProductOK(
                        product.getId(),
                        new ArrayList<>(),
                        agreementsDTO.stream()
                                .map(agr -> agr.getId().toString())
                                .collect(Collectors.toList())
                );
    }
}
