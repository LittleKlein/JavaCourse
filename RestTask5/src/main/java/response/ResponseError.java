package response;

import org.springframework.http.ResponseEntity;

public record ResponseError(
        RestJsonError errorCode,
        String info) implements Response {

    @Override
    public ResponseEntity getHttpResponse() {
        return new ResponseEntity<>(this, RestJsonError.getHttpCode(errorCode()));
    }
}
