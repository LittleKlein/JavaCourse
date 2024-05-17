package modelDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TppRefProductRegisterTypeRepo extends CrudRepository<TppRefProductRegisterType, Long> {
    Optional<TppRefProductRegisterType> findByValue(String value);

    @Query(value = """
            SELECT a.*
            FROM tpp_ref_product_register_type a
            INNER JOIN tpp_ref_product_class b ON b.value_s = a.product_class_code
            WHERE b.value_s  = ?1 and a.account_type = ?2
            """, nativeQuery = true)
    List<TppRefProductRegisterType> findAllProductTypeClient(String value, String type);
}
