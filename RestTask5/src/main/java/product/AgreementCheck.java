package product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.task5.response.ResponseError;
import ru.spring.task5.response.RestJsonError;
import ru.spring.task5.modelDB.AgreementRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AgreementCheck {
    private AgreementRepo agreementRepo;

    @Autowired
    public void setAgreementRepo(AgreementRepo agreementRepo) {
        this.agreementRepo = agreementRepo;
    }

    public Optional<List<ResponseError>> check(List<InstanceArrangement> agreements) {
        final var numbers = agreements.stream()
                .map(agr -> agr.number()).collect(Collectors.toList());
        final var dbAgreements = agreementRepo.findAllByNumberIn(numbers);
        if (dbAgreements.size() > 0) {
            return Optional.of(//new ResponseEntity(
                    dbAgreements.stream()
                            .map(agr -> new ResponseError(RestJsonError.EXISTS
                                    , "Параметр № Дополнительного соглашения (сделки) Number  <" + agr.getNumber() + "> уже существует для ЭП с ИД <" + agr.getTppProduct().getId() + ">"))
                            .collect(Collectors.toList())
                    //, HttpStatus.BAD_REQUEST)
            );
        }
        return Optional.ofNullable(null);
    }
}
