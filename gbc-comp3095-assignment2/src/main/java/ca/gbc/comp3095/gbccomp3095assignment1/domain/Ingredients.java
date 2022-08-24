package ca.gbc.comp3095.gbccomp3095assignment1.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String amount;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipes;

    @OneToMany(mappedBy = "ingredients", cascade = CascadeType.ALL)
    private Set<ShoppingList> shoppingLists = new HashSet<>();

    public Ingredients() {
    }

    public Ingredients(Long id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Ingredients(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public Ingredients(String name, String amount, Recipe recipes) {
        this.name = name;
        this.amount = amount;
        this.recipes = recipes;
    }

    public Ingredients(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Recipe getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
