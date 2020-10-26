package springframework.springrecipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.springrecipeapp.domain.Recipe;

public interface RecipeRepository  extends CrudRepository<Recipe, Long> {
}
