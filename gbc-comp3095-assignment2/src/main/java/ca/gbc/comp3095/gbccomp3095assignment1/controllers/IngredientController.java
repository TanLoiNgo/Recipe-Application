package ca.gbc.comp3095.gbccomp3095assignment1.controllers;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Ingredients;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.IngredientRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.ShoppingListRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IngredientController {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional
    @GetMapping("/ingredients/{id}/remove")
    public String removeIngredient(@PathVariable Long id)
        throws ObjectNotFoundException {

        shoppingListRepository.deleteByIngredientId(id);

        Ingredients ingredient = ingredientRepository.findById(id)
                                                     .orElseThrow(() -> new ObjectNotFoundException("ingredient"));
        Long recipeId = ingredient.getRecipes().getId();
        ingredientRepository.delete(ingredient);
        return "redirect:/recipes/" + recipeId + "/detail";
    }
}
