/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 06/11/2021
        * Description: <Creating Plain Old Java Objects (POJOs), Create Meal POJO,Convert POJOs into JPA Entities>
*/
package ca.gbc.comp3095.gbccomp3095assignment1.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private Date datetime;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User users;

    public Meal() {
    }

    public Meal(String title, Date datetime, String description) {
        this.title = title;
        this.datetime = datetime;
        this.description = description;
    }

    public Meal(String title, Date datetime, String description, User users) {
        this.title = title;
        this.datetime = datetime;
        this.description = description;
        this.users = users;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
