package response;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum RestJsonError {
    NOT_FOUND(400),
    EXISTS(404),
    OK(200),
    BAD_PARAMETER(100);

    private int value;

    @JsonValue
    public int getValue() {
        return value;
    }

    static public HttpStatus getHttpCode(RestJsonError e) {
        return switch (e) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case EXISTS, BAD_PARAMETER -> HttpStatus.BAD_REQUEST;
            case OK -> HttpStatus.OK;
        };
    }
}
