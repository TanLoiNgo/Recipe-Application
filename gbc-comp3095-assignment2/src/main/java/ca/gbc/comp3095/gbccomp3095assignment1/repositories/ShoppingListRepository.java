package ca.gbc.comp3095.gbccomp3095assignment1.repositories;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.ShoppingList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingListRepository
    extends CrudRepository<ShoppingList, Long> {

    @Query("SELECT new ShoppingList(s.id, s.users.id, i.id, i.name, i.amount)"
        + " FROM ShoppingList s"
        + " JOIN Ingredients i ON i.id = s.ingredients.id"
        + " WHERE s.users.id = :userId")
    List<ShoppingList> findByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM ShoppingList sh WHERE sh.ingredients.id = :ingredientId")
    void deleteByIngredientId(@Param("ingredientId") Long ingredientId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value = "DELETE FROM SHOPPING_LIST WHERE USER_ID = :userId AND INGREDIENT_ID = :ingredientId")
    void deleteByUserAndIngredientId(@Param("userId") Long userId, @Param("ingredientId") Long ingredientId);
}
