/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <The controller class that control Favorite view and its model>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.controllers;

import ca.gbc.comp3095.gbccomp3095assignment1.config.CustomUserDetails;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Favorite;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Recipe;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FavoriteController {
    // TODO something...
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Transactional
    @GetMapping("/recipes/{id}/addFavorite")
    public String addFavorite(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = new User(userDetails.getId());
        Recipe recipe = new Recipe(id);
        Favorite favorite = new Favorite(user, recipe);
        favoriteRepository.save(favorite);
        return "redirect:/recipes";
    }

    @Transactional
    @GetMapping("/recipes/{id}/removeFavorite")
    public String removeFavorite(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        favoriteRepository.deleteByUsersAndRecipes(userDetails.getId(), id);
        return "redirect:/recipes";
    }

}
