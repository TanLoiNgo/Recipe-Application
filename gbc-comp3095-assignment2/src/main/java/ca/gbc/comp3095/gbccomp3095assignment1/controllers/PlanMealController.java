/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <The controller class that control PlanMeal view and its model>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.controllers;

import ca.gbc.comp3095.gbccomp3095assignment1.config.CustomUserDetails;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Meal;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import ca.gbc.comp3095.gbccomp3095assignment1.dto.UpdateMealRequest;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class PlanMealController {

    @Autowired
    private MealRepository mealRepository;


    @GetMapping("/addMeal")
    public String addMeal(Model model) {
        model.addAttribute("meals", new Meal());
        return "Meal/plan_meal";
    }


    @Transactional
    @PostMapping("/addMeal")
    public String addMeal(Meal meal, @AuthenticationPrincipal CustomUserDetails userDetails) {
        meal.setUsers(new User(userDetails.getId()));
        mealRepository.save(meal);
        return "redirect:/mealList";
    }

    @Transactional
    @PostMapping("/saveMeals")
    public String addMeal(UpdateMealRequest request) {
        Map<Long, Meal> mealMap = request.getMeals().stream()
                                         .collect(Collectors.toMap(Meal::getId, Function.identity()));
        mealRepository.findAllById(mealMap.keySet())
                      .forEach(meal -> {
                          meal.setTitle(mealMap.get(meal.getId()).getTitle());
                          meal.setDescription(mealMap.get(meal.getId()).getDescription());
                          mealRepository.save(meal);
                      });

        return "redirect:/mealList";
    }

    @Transactional
    @GetMapping("/meals/{id}/remove")
    public String addMeal(@PathVariable Long id) {
        mealRepository.deleteById(id);
        return "redirect:/mealList";
    }

    @GetMapping("/mealList")
    public String MealList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long currentUserId = userDetails.getId();

        List<Meal> meals = mealRepository.findMealByUserId(currentUserId);
        model.addAttribute("updateMealRequest", new UpdateMealRequest(meals));
        return "Meal/meal_list";
    }
}
