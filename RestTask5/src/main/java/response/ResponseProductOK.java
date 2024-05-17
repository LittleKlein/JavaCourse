package response;

import java.util.List;

public record ResponseProductOK(
        ResponseProductOKData data,
        List<String> registerId,
        List<String> supplementaryAgreementId
) implements Response {
    record ResponseProductOKData(Long instanceId) {
    }

    public ResponseProductOK(Long instanceId, List<String> listRegister, List<String> listAgreement) {
        this(
                new ResponseProductOKData(instanceId),
                listRegister,
                listAgreement
        );
    }
}
