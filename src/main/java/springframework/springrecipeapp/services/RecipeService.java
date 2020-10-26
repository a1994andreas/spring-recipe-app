package springframework.springrecipeapp.services;

import springframework.springrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

}
