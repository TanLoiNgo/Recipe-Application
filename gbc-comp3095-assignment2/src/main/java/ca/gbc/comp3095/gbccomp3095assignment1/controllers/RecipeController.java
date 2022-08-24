/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <The controller class that control Recipe view and its model>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.controllers;

import ca.gbc.comp3095.gbccomp3095assignment1.config.CustomUserDetails;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Ingredients;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Recipe;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.IngredientRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.RecipeRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.UserRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class RecipeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/addRecipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe/add_recipe";
    }

    @Transactional
    @PostMapping("/addRecipe")
    public String addRecipe(Recipe recipe, @AuthenticationPrincipal CustomUserDetails userDetails) {
        recipe.getIngredients().forEach(ingredient -> ingredient.setRecipes(recipe));
        recipe.setUsers(new User(userDetails.getId()));
        recipeRepository.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes")
    public String searchRecipes(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Recipe> recipes = recipeRepository.searchRecipes(keyword);
        model.addAttribute("recipes", recipes);
        model.addAttribute("currentUserId", userDetails.getId());
        return "recipe/recipe_list";
    }

    @GetMapping("/recipes/{recipeId}/detail")
    public String fetchIngredients(Model model, @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("recipeId") Long recipeId)
            throws ObjectNotFoundException {
        List<Ingredients> ingredients = ingredientRepository.fetchByRecipeId(recipeId);

        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new ObjectNotFoundException("recipe"));
        recipe.setIngredients(ingredients);

        model.addAttribute("recipe", recipe);
        model.addAttribute("currentUserId", userDetails.getId());

        return "recipe/recipe_detail";
    }

    @Transactional
    @PostMapping("/saveRecipe")
    public String addRecipe(Recipe recipe) {
        Map<Long, Ingredients> ingredientsMap = recipe.getIngredients()
                .stream()
                .collect(Collectors.toMap(Ingredients::getId, Function.identity()));
        List<Ingredients> ingredients = ingredientRepository.fetchByRecipeId(recipe.getId());
        ingredients.forEach(ingredientUpdate -> {
            Ingredients ingredient = ingredientsMap.get(ingredientUpdate.getId());
            ingredientUpdate.setName(ingredient.getName());
            ingredientUpdate.setAmount(ingredient.getAmount());
            ingredientRepository.save(ingredientUpdate);
        });
//        return "redirect:/recipes";
        return "redirect:/recipes/" + recipe.getId() + "/detail";
    }

    @GetMapping("/myRecipe")
    public String myRecipe(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long currentUserId = userDetails.getId();

        List<Recipe> recipes = recipeRepository.findByUserId(currentUserId);
        List<Recipe> favoriteRecipes = recipeRepository.findFavoriteByUserId(currentUserId);
//        List<Recipe> ingredients = recipeRepository.findIngredientByUserId(currentUserId);

        User user = userRepository.getById(currentUserId);

        model.addAttribute("user", user);
        model.addAttribute("recipes", recipes);
        model.addAttribute("favoriteRecipes", favoriteRecipes);
        //model.addAttribute("ingredients", ingredients);

        return "recipe/myRecipe";
    }

    @GetMapping("/myFavoriteRecipe")
    public String myFavoriteRecipe(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long currentUserId = userDetails.getId();

        List<Recipe> recipes = recipeRepository.findByUserId(currentUserId);
        List<Recipe> favoriteRecipes = recipeRepository.findFavoriteByUserId(currentUserId);
//        List<Recipe> ingredients = recipeRepository.findIngredientByUserId(currentUserId);

        User user = userRepository.getById(currentUserId);

        model.addAttribute("user", user);
        model.addAttribute("recipes", recipes);
        model.addAttribute("favoriteRecipes", favoriteRecipes);
        //model.addAttribute("ingredients", ingredients);

        return "recipe/favoriteRecipe";
    }
}
