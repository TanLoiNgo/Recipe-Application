/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <The helper class to check if the recipe is the user’s favorite>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.helpers;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Favorite;

import java.util.List;

public class FavoriteHelper {
    private FavoriteHelper() {
    }

    public static boolean contains(List<Favorite> favorites, Long userId, Long recipeId) {
        return favorites.stream()
                .anyMatch(favorite -> favorite.getUsers().getId().equals(userId)
                        && favorite.getRecipes().getId().equals(recipeId));
    }
}
