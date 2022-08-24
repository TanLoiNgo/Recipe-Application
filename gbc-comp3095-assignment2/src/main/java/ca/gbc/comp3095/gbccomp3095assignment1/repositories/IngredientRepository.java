package ca.gbc.comp3095.gbccomp3095assignment1.repositories;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Ingredients;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository
    extends CrudRepository<Ingredients, Long> {

    @Query("SELECT i FROM Ingredients i WHERE i.recipes.id = :recipeId")
    List<Ingredients> fetchByRecipeId(@Param("recipeId") Long recipeId);

    @Query("SELECT i FROM Ingredients i WHERE i.recipes.id = :recipeId")
    List<Ingredients> fetchByRecipeIdAndUserId(@Param("recipeId") Long recipeId);

    @Query(nativeQuery = true,
            value = "SELECT i.* FROM Ingredients i JOIN RECIPE r ON r.id = i.RECIPE_ID WHERE r.USER_ID = :userId")
    List<Recipe> findIngredientsByUserId(@Param("userId") Long userId);
}
