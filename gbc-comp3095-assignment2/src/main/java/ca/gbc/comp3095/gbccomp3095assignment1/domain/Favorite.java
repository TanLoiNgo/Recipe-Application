/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <Creating Plain Old Java Objects (POJOs), Create Favorite POJO,Convert POJOs into JPA Entities>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipes;

    public Favorite() {
    }

    public Favorite(User users, Recipe recipes) {
        this.users = users;
        this.recipes = recipes;
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
        Favorite favorite = (Favorite) o;
        return Objects.equals(id, favorite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
