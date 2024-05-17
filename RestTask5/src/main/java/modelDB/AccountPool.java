package modelDB;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "account_pool")
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Column(name = "branch_code", length = 50)
    String branchCode;

    @Column(name = "currency_code", length = 30)
    String currencyCode;

    @Column(name = "mdm_code", length = 50)
    String mdmCode;

    @Column(name = "priority_code", length = 30)
    String priorityCode;

    @Column(name = "registry_type_code", length = 50)
    String registryTypeCode;

}
