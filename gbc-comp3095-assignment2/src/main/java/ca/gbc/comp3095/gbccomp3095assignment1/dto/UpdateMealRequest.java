package ca.gbc.comp3095.gbccomp3095assignment1.dto;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Meal;

import java.util.List;

public class UpdateMealRequest {
    private List<Meal> meals;

    public UpdateMealRequest(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(final List<Meal> meals) {
        this.meals = meals;
    }
}
