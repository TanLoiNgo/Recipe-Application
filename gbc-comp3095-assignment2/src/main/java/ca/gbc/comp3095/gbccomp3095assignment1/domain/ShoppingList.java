package ca.gbc.comp3095.gbccomp3095assignment1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredients ingredients;

    public ShoppingList() {
    }

    public ShoppingList(Long id, Long userId, Long ingredientId, String ingredientName, String ingredientAmount) {
        this.id = id;
        this.users = new User(userId);
        this.ingredients = new Ingredients(ingredientId, ingredientName, ingredientAmount);
    }

    public ShoppingList(User users, Ingredients ingredients) {
        this.users = users;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
