package ca.gbc.comp3095.gbccomp3095assignment1.helpers;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Favorite;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.ShoppingList;

import java.util.List;

public class ShoppingListHelper {
    private ShoppingListHelper() {
    }

    public static boolean contains(List<ShoppingList> shoppingLists, Long userId, Long ingredientId) {
        return shoppingLists.stream()
                .anyMatch(shoppingList -> shoppingList.getUsers().getId().equals(userId)
                        && shoppingList.getIngredients().getId().equals(ingredientId));
    }
}
