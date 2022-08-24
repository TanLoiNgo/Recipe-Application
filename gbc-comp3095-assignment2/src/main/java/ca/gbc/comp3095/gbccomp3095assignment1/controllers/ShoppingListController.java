package ca.gbc.comp3095.gbccomp3095assignment1.controllers;

import ca.gbc.comp3095.gbccomp3095assignment1.config.CustomUserDetails;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Ingredients;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.ShoppingList;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.IngredientRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.ShoppingListRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.UserRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ShoppingListController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingListRepository shoppingListRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Transactional
    @GetMapping("/ingredients/{ingredientId}/addShoppingList")
    public String addShoppingList(@PathVariable("ingredientId") Long ingredientId, @AuthenticationPrincipal CustomUserDetails userDetails)
        throws ObjectNotFoundException {

        Ingredients ingredient = ingredientRepository.findById(ingredientId)
                                                     .orElseThrow(() -> new ObjectNotFoundException("ingredient"));

        User user = new User(userDetails.getId());
        ShoppingList shoppingList = new ShoppingList(user, ingredient);
        shoppingListRepository.save(shoppingList);

        return "redirect:/recipes/" + ingredient.getRecipes().getId() + "/detail";
    }

    @Transactional
    @GetMapping("/ingredients/{ingredientId}/removeShoppingList")
    public String removeShoppingList(@PathVariable("ingredientId") Long ingredientId, @AuthenticationPrincipal CustomUserDetails userDetails)
        throws ObjectNotFoundException {

        Ingredients ingredient = ingredientRepository.findById(ingredientId)
                                                     .orElseThrow(() -> new ObjectNotFoundException("ingredient"));

        shoppingListRepository.deleteByUserAndIngredientId(userDetails.getId(), ingredientId);
        //return "redirect:/recipes";
        return "redirect:/recipes/" + ingredient.getRecipes().getId() + "/detail";
    }

    @GetMapping("/myŚhoppingList")
    public String profile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails)
        throws ObjectNotFoundException {
        Long currentUserId = userDetails.getId();

        User user = userRepository.findById(currentUserId).orElseThrow(() -> new ObjectNotFoundException("user"));

        List<ShoppingList> shoppingLists = shoppingListRepository.findByUserId(currentUserId);

        model.addAttribute("user", user);
        model.addAttribute("shoppingLists", shoppingLists);

        return "ShoppingList/viewShoppingList";
    }
}
