/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <This java file is initializing Data with Spring from H2 Console(Users, Recipes, Meals, Favorite)>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.bootstrap;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Favorite;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Ingredients;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Meal;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.Recipe;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.ShoppingList;
import ca.gbc.comp3095.gbccomp3095assignment1.domain.User;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.FavoriteRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.IngredientRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.MealRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.RecipeRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.ShoppingListRepository;
import ca.gbc.comp3095.gbccomp3095assignment1.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class bootStrapData
        implements CommandLineRunner {

    private UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final MealRepository mealRepository;
    private final FavoriteRepository favoriteRepository;
    private final IngredientRepository ingredientRepository;
    private final ShoppingListRepository shoppingListRepository;

    public bootStrapData(UserRepository userRepository, RecipeRepository recipeRepository, MealRepository mealRepository, FavoriteRepository favoriteRepository, IngredientRepository ingredientRepository, ShoppingListRepository shoppingListRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
        this.favoriteRepository = favoriteRepository;
        this.ingredientRepository = ingredientRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public void run(String... args)
            throws Exception {

        //Create  Users
        User user1 = new User("Tan Loi", "Ngo", "JackNgo", "$2a$10$zi34r96q4i9i5YA.m1agje98YEFv3WiqLLWk0VwLjWiYCUgq3Y8Ya", "tanloi.ngo@georgebrown.ca");
        User user2 = new User("Minh Thanh", "Duong", "MinhThanh", "$2a$10$zi34r96q4i9i5YA.m1agje98YEFv3WiqLLWk0VwLjWiYCUgq3Y8Ya", "minhthanh.duong@georgebrown.ca");
        User user3 = new User("Thi Kim Thao", "Tran", "MinTran", "$2a$10$zi34r96q4i9i5YA.m1agje98YEFv3WiqLLWk0VwLjWiYCUgq3Y8Ya", "thikimthao.tran@georgebrown.ca");
        User user4 = new User("Hoang Thinh", "Tran", "ThinhTran", "$2a$10$zi34r96q4i9i5YA.m1agje98YEFv3WiqLLWk0VwLjWiYCUgq3Y8Ya", "hoangthinh.tran@georgebrown.ca");
        User user5 = new User("Tornado", "The", "Tornado", "$2a$10$zi34r96q4i9i5YA.m1agje98YEFv3WiqLLWk0VwLjWiYCUgq3Y8Ya", "tester@georgebrown.ca");

        //Create a recipe
        Recipe recipe1 = new Recipe("Vietnamese Pho Noodle Soup", "Quick boil – Remove impurities from beef with a 5 minute boil, it’s the path to a beautiful clear soup; " +
                "", "6 hours");
        Recipe recipe2 = new Recipe("Crab soup", "boil chiken bone,....", "2 hours");
        Recipe recipe3 = new Recipe("Cinnamon bun", "mixing sugar, salt, butter", "4 hours");
        Recipe recipe4 = new Recipe("deep-fried fish", "deep-fried fish", "20 minutes");
        Recipe recipe5 = new Recipe("Banana bread", "mixing sugar, salt, butter", "2 hours");

        //Ingredients

        Ingredients ingredient1 = new Ingredients("Beef bone", "4", recipe1);
        Ingredients ingredient2 = new Ingredients("Crab meat", "2 lbs", recipe2);
        Ingredients ingredient3 = new Ingredients("cinnamon powder", "2 tbsp", recipe3);
        Ingredients ingredient4 = new Ingredients("fish", "2 lbs", recipe4);
        Ingredients ingredient5 = new Ingredients("Banana", "3", recipe5);

        ShoppingList shoppingList1 = new ShoppingList(user1, ingredient1);
        ShoppingList shoppingList2 = new ShoppingList(user2, ingredient2);
        ShoppingList shoppingList3 = new ShoppingList(user3, ingredient3);
        ShoppingList shoppingList4 = new ShoppingList(user4, ingredient4);
        ShoppingList shoppingList5 = new ShoppingList(user5, ingredient5);


        //Create a favorite
        Favorite favorite1 = new Favorite(user1, recipe1);
        Favorite favorite2 = new Favorite(user2, recipe1);
        Favorite favorite3 = new Favorite(user3, recipe1);
        Favorite favorite4 = new Favorite(user4, recipe1);
        Favorite favorite5 = new Favorite(user5, recipe1);


        //Create Meals
        Meal meal1 = new Meal("Vietnamese Pho Noodle Soup", new Date(), "Pho for dinner");
        Meal meal2 = new Meal("Stir-fried Veggies", new Date(), " for Lunch");
        Meal meal3 = new Meal("crab soup", new Date(), " for Lunch");
        Meal meal4 = new Meal("sushi", new Date(), " for Dinner");
        Meal meal5 = new Meal("Pizza", new Date(), " for Dinner");


        meal1.setUsers(user1);
        meal2.setUsers(user2);
        meal3.setUsers(user3);
        meal4.setUsers(user4);
        meal5.setUsers(user5);


        user1.getRecipes().add(recipe1);
        user1.getFavorites().add(favorite1);
        user1.getMeals().add(meal1);
        user1.getShoppingLists().add(shoppingList1);

        user2.getRecipes().add(recipe2);
        user2.getFavorites().add(favorite2);
        user2.getMeals().add(meal2);
        user2.getShoppingLists().add(shoppingList2);


        user3.getRecipes().add(recipe3);
        user3.getFavorites().add(favorite3);
        user3.getMeals().add(meal3);
        user3.getShoppingLists().add(shoppingList3);

        user4.getRecipes().add(recipe4);
        user4.getFavorites().add(favorite4);
        user4.getMeals().add(meal4);
        user4.getShoppingLists().add(shoppingList4);

        user5.getRecipes().add(recipe5);
        user5.getFavorites().add(favorite5);
        user5.getMeals().add(meal5);
        user5.getShoppingLists().add(shoppingList5);


        recipe1.setUsers(user1);
        recipe1.getFavorites().add(favorite1);
        recipe1.getIngredients().add(ingredient1);

        recipe2.setUsers(user2);
        recipe2.getFavorites().add(favorite2);
        recipe2.getIngredients().add(ingredient2);

        recipe3.setUsers(user3);
        recipe3.getFavorites().add(favorite3);
        recipe3.getIngredients().add(ingredient3);

        recipe4.setUsers(user4);
        recipe4.getFavorites().add(favorite4);
        recipe4.getIngredients().add(ingredient4);

        recipe5.setUsers(user5);
        recipe5.getFavorites().add(favorite5);
        recipe5.getIngredients().add(ingredient5);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        mealRepository.save(meal1);
        mealRepository.save(meal2);
        mealRepository.save(meal3);
        mealRepository.save(meal4);
        mealRepository.save(meal5);

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);
        recipeRepository.save(recipe3);
        recipeRepository.save(recipe4);
        recipeRepository.save(recipe5);


        favoriteRepository.save(favorite1);
        favoriteRepository.save(favorite2);
        favoriteRepository.save(favorite3);
        favoriteRepository.save(favorite4);
        favoriteRepository.save(favorite5);

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);
        ingredientRepository.save(ingredient5);

        shoppingListRepository.save(shoppingList1);
        shoppingListRepository.save(shoppingList2);
        shoppingListRepository.save(shoppingList3);
        shoppingListRepository.save(shoppingList4);
        shoppingListRepository.save(shoppingList5);

        System.out.println("Started in Bootstrap...");
        System.out.println("Number of Users " + userRepository.count());
        System.out.println("Number of Recipes " + recipeRepository.count());
        System.out.println("Number of Meals " + mealRepository.count());
        System.out.println("Number of Favorites " + favoriteRepository.count());
        System.out.println("Number of Ingredients " + ingredientRepository.count());
        System.out.println("Number of Shopping List " + shoppingListRepository.count());


    }
}
