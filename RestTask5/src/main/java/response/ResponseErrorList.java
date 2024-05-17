package response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public record ResponseErrorList(
        List<ResponseError> errors
) implements Response{
    @Override
    public ResponseEntity getHttpResponse() {
        return new ResponseEntity<>(this, HttpStatus.BAD_REQUEST);
    }
}
