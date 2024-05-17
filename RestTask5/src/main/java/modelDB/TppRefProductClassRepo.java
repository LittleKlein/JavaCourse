package modelDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TppRefProductClassRepo extends CrudRepository<TppRefProductClass, Long> {
}
