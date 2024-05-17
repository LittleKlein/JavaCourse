package response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public sealed interface Response permits ResponseError, ResponseErrorList, ResponseProductOK, ResponseProductRegisterOK {
    @JsonIgnore
    default ResponseEntity getHttpResponse(){
        return new ResponseEntity(this, HttpStatus.OK);
    };

}
