package springframework.springrecipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.springrecipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository  extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
