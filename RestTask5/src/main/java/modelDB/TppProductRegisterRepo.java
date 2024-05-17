package modelDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TppProductRegisterRepo extends CrudRepository<TppProductRegister, Long> {
    @Query(value = """
            SELECT CASE WHEN count(*) > 0 THEN true ELSE false END 
            FROM tpp_product_register a 
            WHERE a.product_id  = ?1 and a.type = ?2
            """, nativeQuery = true)
    boolean existsProductType(Long product_id, String type);
}
