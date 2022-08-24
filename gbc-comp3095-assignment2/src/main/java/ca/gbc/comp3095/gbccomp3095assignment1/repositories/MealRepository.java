/*
* Project: < Recipe Application >
        * Assignment: < assignment #1 >
        * Author(s)-Student Number:
        < Tan Loi Ngo - 101094358 >
        < Minh Thanh Duong - 101281722 >
        < Thi Kim Thao Tran - 101156327 >
        < Hoang Thinh Tran - 101133062 >
        * Date: 05/11/2021
        * Description: <Implement Spring Data JPA Repositories, The repository interface that define method to get Meal by user>
*/

package ca.gbc.comp3095.gbccomp3095assignment1.repositories;

import ca.gbc.comp3095.gbccomp3095assignment1.domain.Meal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository
    extends CrudRepository<Meal, Long> {

    @Query("SELECT m FROM Meal m WHERE m.users.id = :userId")
    List<Meal> findMealByUserId(@Param("userId") Long currentUserId);
}
