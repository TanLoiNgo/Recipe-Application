/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <Creating Plain Old Java Objects (POJOs), Create Recipe POJO,Convert POJOs into JPA Entities>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String instruction;
    private String cookTime;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User users;

    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private Set<Favorite> favorites = new HashSet<>();


    @OneToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private List<Ingredients> ingredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(Long id) {
        this.id = id;
    }

    public Recipe(String title, String instruction, String cookTime) {
        this.title = title;
        this.instruction = instruction;
        this.cookTime = cookTime;
    }

    public Recipe(String title, String instruction, String cookTime, User users) {
        this.title = title;
        this.instruction = instruction;
        this.cookTime = cookTime;
        this.users = users;
    }

    public Recipe(String title, String instruction, String cookTime, User users, Set<Favorite> favorites) {
        this.title = title;
        this.instruction = instruction;
        this.cookTime = cookTime;
        this.users = users;
        this.favorites = favorites;
    }

    public Recipe(Long id, String title, String instruction, String cookTime,
                  User users, Set<Favorite> favorites, List<Ingredients> ingredients) {
        this.id = id;
        this.title = title;
        this.instruction = instruction;
        this.cookTime = cookTime;
        this.users = users;
        this.favorites = favorites;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
