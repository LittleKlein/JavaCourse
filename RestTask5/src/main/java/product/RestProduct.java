package product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProduct {
    private ProductCreate productCreate;
    private AgreementCreate agreementCreate;

    @Autowired
    public void setProductKeep(ProductCreate productCreate) {
        this.productCreate = productCreate;
    }

    @Autowired
    public void setAgreementCreate(AgreementCreate agreementCreate) {
        this.agreementCreate = agreementCreate;
    }

    @PostMapping("/corporate-settlement-instance/create")
    public ResponseEntity handle(@Valid @RequestBody ProductKeepJson dto) {
        if (dto.instanceId() == null) {
            productCreate.process(dto);
            return productCreate.getResponse().getHttpResponse();
        } else {
            return agreementCreate.process(dto).getHttpResponse();
        }
    }
}
