package modelDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepo extends CrudRepository<Account, Long> {
    @Query(value = """
            SELECT a.* FROM account a
            INNER JOIN account_pool b ON a.account_pool_id= b.id
            WHERE b.branch_code = ?1
                and b.currency_code = ?2
                and b.mdm_code = ?3
                and b.priority_code = ?4
                and b.registry_type_code = ?5 
            LIMIT 1           
            """, nativeQuery = true)
    Optional<Account> getAccountByPool(
            String branchCode,
            String currencyCode,
            String mdmCode,
            String priorityCode,
            String registryTypeCode
    );
}
