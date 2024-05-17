package register;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProductRegister {

    private ProductRegisterCreate productRegisterCreate;

    @Autowired
    public void setProductRegister(ProductRegisterCreate productRegisterCreate) {
        this.productRegisterCreate = productRegisterCreate;
    }

    @PostMapping("/corporate-settlement-account/create")
    public ResponseEntity handle(@Valid @RequestBody ProductRegisterJson dto) {
        return productRegisterCreate.process(dto).getHttpResponse();
    }
}
