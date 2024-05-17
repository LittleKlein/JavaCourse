package modelDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @Getter @Setter
    private TppProduct tppProduct;

    @Column(name = "general_agreement_id", length = 50)
    @Getter @Setter
    private String generalAgreementId;

    @Column(name = "supplementary_agreement_id", length = 50)
    @Getter @Setter
    private String supplementary_agreement_id;

    @Column(name = "arrangement_type", length = 50)
    @Getter
    private String arrangementType;

    @Column(name = "sheduler_job_id")
    @Getter @Setter
    private Long shedulerJobId;

    @Column(name = "number", length = 50)
    @Getter @Setter
    private String number;

    @Column(name = "opening_date")
    @Getter @Setter
    private Timestamp openingDate;

    @Column(name = "closing_date")
    @Getter @Setter
    private Timestamp closingDate;

    @Column(name = "cancel_date")
    @Getter @Setter
    private Timestamp cancelDate;

    @Column(name = "validity_duration")
    @Getter @Setter
    private Integer validityDuration;

    @Column(name = "cancellation_reason", length = 100)
    @Getter @Setter
    private String cancellationReason;

    @Column(name = "status", length = 100)
    @Getter @Setter
    private String status;

    @Column(name = "interest_calculation_date")
    @Getter @Setter
    private Timestamp interestCalculationDate;

    @Column(name = "interest_rate")
    @Getter @Setter
    private BigDecimal interestRate;

    @Column(name = "coefficient")
    @Getter @Setter
    private BigDecimal coefficient;

    @Column(name = "coefficient_action", length = 50)
    @Getter @Setter
    private String coefficientAction;

    @Column(name = "minimum_interest_rate")
    @Getter @Setter
    private BigDecimal minimumInterestRate;

    @Column(name = "minimum_interest_rate_coefficient")
    @Getter @Setter
    private BigDecimal minimumInterestRateCoefficient;

    @Column(name = "minimum_interest_rate_coefficient_action", length = 50)
    @Getter @Setter
    private String minimumInterestRateCoefficientAction;

    @Column(name = "maximal_interest_rate")
    @Getter @Setter
    private BigDecimal maximalInterestRate;

    @Column(name = "maximal_interest_rate_coefficient")
    @Getter @Setter
    private BigDecimal maximalInterestRateCoefficient;

    @Column(name = "maximal_interest_rate_coefficient_action", length = 50)
    @Getter @Setter
    private String maximalInterestRateCoefficientAction;

    public void setArrangementType(TppProductType tppProductType) {
        this.arrangementType = tppProductType.getName();
    }
}


