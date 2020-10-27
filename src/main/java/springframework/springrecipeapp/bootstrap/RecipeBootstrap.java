package springframework.springrecipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import springframework.springrecipeapp.domain.Ingredient;
import springframework.springrecipeapp.domain.Notes;
import springframework.springrecipeapp.domain.Recipe;
import springframework.springrecipeapp.domain.UnitOfMeasure;
import springframework.springrecipeapp.repositories.CategoryRepository;
import springframework.springrecipeapp.repositories.RecipeRepository;
import springframework.springrecipeapp.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository  categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setDescription("One random new recipe desc here :)");
        // Get Unit of Measure
        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!optionalUnitOfMeasure.isPresent())
            throw new RuntimeException("Expected UOM Not found");

        UnitOfMeasure teaspoon = optionalUnitOfMeasure.get();

        // Add test ingredients
        for(int i=0; i<5; i++)
            recipe.getIngredients().add(new Ingredient("ingredient name"+i, new BigDecimal(2), teaspoon,recipe));

        // Add notes
        Notes notes = new Notes(recipe,"test notes here..");
        recipe.setNotes(notes);


        recipes.add(recipe);
        return recipes;

    }

}
