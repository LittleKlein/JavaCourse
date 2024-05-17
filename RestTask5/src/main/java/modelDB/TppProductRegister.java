package modelDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tpp_product_register")
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "product_id")
    @Getter @Setter
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "value_s")
    @Getter @Setter
    private TppRefProductRegisterType tppRefProductRegisterType;

    @Column(name = "account")
    @Getter @Setter
    private Long account;

    @Column(name = "currency_code", length = 50, nullable = false)
    @Getter @Setter
    private String currencyCode;

    @Column(name = "state", length = 50, nullable = false)
    @Getter
    private String state;

    @Column(name = "account_number", length = 25, nullable = false)
    @Getter @Setter
    private String accountNumber;

    public void setState(TppProductRegisterState tppProductRegisterState) {
        this.state = tppProductRegisterState.getName();
    }
}
