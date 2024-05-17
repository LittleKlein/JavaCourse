package validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.spring.task5.response.ResponseError;
import ru.spring.task5.response.RestJsonError;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<ResponseError> responseErrors = e.getBindingResult().getFieldErrors().stream()
                .map(
                        error -> new ResponseError(
                                RestJsonError.BAD_PARAMETER
                                , "Имя обязательного параметра <" + error.getField() + "> не заполнено")
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(responseErrors);
    }
}


record ValidationErrorResponse(
        List<ResponseError> errorsValidate
) {
}

