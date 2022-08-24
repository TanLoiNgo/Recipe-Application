/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <The controller class that control User view and its model>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.controllers;

import ca.gbc.comp3095.gbccomp3095assignment1.config.CustomUserDetails;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Recipe;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.IngredientRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.RecipeRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String index() {
        return "home/index";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long currentUserId = userDetails.getId();

        List<Recipe> recipes = recipeRepository.findByUserId(currentUserId);
        List<Recipe> favoriteRecipes = recipeRepository.findFavoriteByUserId(currentUserId);
        //List<Recipe> shoppingLists = recipeRepository.findIngredientByUserId(currentUserId);

        User user = userRepository.getById(currentUserId);

        model.addAttribute("user", user);
        model.addAttribute("recipes", recipes);
        //model.addAttribute("shoppingLists", shoppingLists);
        model.addAttribute("favoriteRecipes", favoriteRecipes);
        //model.addAttribute("ingredients", ingredients);

        return "user/profile";
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "home/login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "register/signup_form";
    }

    @PostMapping("process_register")
    public String processRegister(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "register/register_success";
    }

//    @GetMapping("/list_users")
//    public String viewUsersList() {
//        return "user_list";
//    }
}
