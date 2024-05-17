package response;

public record ResponseProductRegisterOK(ResponseProductRegisterOKData data) implements Response {
    public record ResponseProductRegisterOKData(Long accountId) {
    }

    public ResponseProductRegisterOK(Long accountId) {
        this(new ResponseProductRegisterOKData(accountId));
    }
}
