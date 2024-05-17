package product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Valid
public record ProductKeepJson(
        Long instanceId,
        @NotBlank String productType,
        @NotBlank String productCode,
        @NotNull Long registerType,
        @NotBlank String mdmCode,
        @NotBlank String contractNumber,
        @NotNull Timestamp contractDate,
        @NotNull Integer priority,
        BigDecimal interestRatePenalty,
        BigDecimal minimalBalance,
        BigDecimal thresholdAmount,
        String accountingDetails,
        String rateType,
        BigDecimal taxPercentageRate,
        BigDecimal technicalOverdraftLimitAmount,
        @NotNull Long contractId,
        @NotBlank String branchCode,
        @NotBlank String isoCurrencyCode,
        @NotBlank String urgencyCode,
        Long referenceCode,
        List<AdditionalPropertiesVip> data,
        List<InstanceArrangement> instanceArrangement
) {
}

record AdditionalPropertiesVip(
        String key,
        String value
) {
}

record InstanceArrangement(
        String generalAgreementId,
        String supplementaryAgreementId,
        String arrangementType,
        Long shedulerJobId,
        @NotBlank String number,
        @NotBlank Timestamp openingDate,
        Timestamp closingDate,
        Timestamp cancelDate,
        Integer validityDuration,
        String cancellationReason,
        String status,
        Timestamp interestCalculationDate,
        BigDecimal interestRate,
        BigDecimal coefficient,
        String coefficientAction,
        BigDecimal minimumInterestRate,
        BigDecimal minimumInterestRateCoefficient,
        String minimumInterestRateCoefficientAction,
        BigDecimal maximaInterestRate,
        BigDecimal maximaInterestRateCoefficient,
        String maximaInterestRateCoefficientAction
) {
}