package modelDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "tpp_product")
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code_id", referencedColumnName = "internal_id")
    @Getter @Setter
    private TppProductRegister tppProductRegister;

    @Column(name = "client_id")
    @Getter @Setter
    private Long clientId;

    @Column(name = "type", length = 50)
    @Getter
    private String type;

    @Column(name = "number", length = 50)
    @Getter @Setter
    private String number;

    @Column(name = "priority")
    @Getter @Setter
    private Integer priority;

    @Column(name = "date_of_conclusion")
    @Getter @Setter
    private Timestamp dateOfConclusion;

    @Column(name = "start_date_time")
    @Getter @Setter
    private Timestamp startDateTime;

    @Column(name = "end_date_time")
    @Getter @Setter
    private Timestamp endDateTime;

    @Column(name = "days")
    @Getter @Setter
    private Integer days;

    @Column(name = "penalty_rate")
    @Getter @Setter
    private BigDecimal penaltyRate;

    @Column(name = "nso")
    @Getter @Setter
    private BigDecimal nso;

    @Column(name = "threshold_amount")
    @Getter @Setter
    private BigDecimal thresholdAmount;

    @Column(name = "requisite_type", length = 50)
    @Getter @Setter
    private String requisiteType;

    @Column(name = "interest_rate_type", length = 50)
    @Getter @Setter
    private String interestRateType;

    @Column(name = "tax_rate")
    @Getter @Setter
    private BigDecimal taxRate;

    @Column(name = "reasone_close", length = 100)
    @Getter @Setter
    private String reasoneClose;

    @Column(name = "state", length = 100)
    @Getter
    private String state;

    public void setType(TppProductType tppProductType) {
        this.type = tppProductType.getName();
    }

    public void setState(TppProductState tppProductState) {
        this.state = tppProductState.getName();
    }
}