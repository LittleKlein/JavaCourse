package register;


import jakarta.validation.constraints.NotNull;

public record ProductRegisterJson(
        @NotNull Long instanceId,
        String registryTypeCode,
        String accountType,
        String currencyCode,
        String branchCode,
        String priorityCode,
        String mdmCode,
        String clientCode,
        String trainRegion,
        String counter,
        String salesCode
) {
    public ProductRegisterJson(Long instanceId, String registryTypeCode, String accountType, String currencyCode, String branchCode, String priorityCode, String mdmCode) {
        this(instanceId, registryTypeCode, accountType, currencyCode, branchCode, priorityCode, mdmCode, null, null, null, null);
    }
}
