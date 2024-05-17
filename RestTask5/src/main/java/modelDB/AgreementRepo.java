package modelDB;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AgreementRepo extends CrudRepository<Agreement, Long> {
    List<Agreement> findAllByNumberIn(List<String> number);
}
