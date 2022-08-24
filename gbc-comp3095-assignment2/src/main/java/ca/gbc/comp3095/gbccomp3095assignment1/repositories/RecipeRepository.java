/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 05/11/2021
        * Description: <Implement Spring Data JPA Repositories, The repository interface that define method to get Recipe>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.repositories;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository
        extends CrudRepository<Recipe, Long> {

    @Query( "SELECT  r FROM  Recipe r wHERE r.users.id = :userId ")
    List<Recipe> findByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true,
            value = "SELECT r.* FROM RECIPE r JOIN FAVORITE f ON r.id = f.RECIPE_ID WHERE f.USER_ID = :userId")
    List<Recipe> findFavoriteByUserId(@Param("userId") Long userId);

//    @Query(nativeQuery = true,
//            value = "SELECT i.* FROM INGREDIENTS i JOIN SHOPPING_LIST s ON i.ID = s.INGREDIENT_ID WHERE s.USER_ID = :userId")
//    List<Recipe> findIngredientByUserId(@Param("userId") Long userId);

//    @Query(nativeQuery = true,
//            value = "SELECT ing.* FROM INGREDIENTS ing JOIN RECIPE r ON r.id = ing.RECIPE_ID wHERE r.USER_ID =  :userId")
//    List<Recipe> findIngredientByUserId(@Param("userId") Long userId);

    @Query("SELECT r FROM Recipe r"
            + " WHERE :keyword IS NULL OR r.title LIKE %:keyword%"
            + " ORDER BY r.id DESC")
    List<Recipe> searchRecipes(String keyword);

}
