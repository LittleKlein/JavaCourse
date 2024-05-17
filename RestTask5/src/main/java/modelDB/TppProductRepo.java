package modelDB;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TppProductRepo extends CrudRepository<TppProduct, Long> {
    Optional<TppProduct> findFirstByNumber(String number);
}
