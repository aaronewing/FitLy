package com.ewing.capstoneproj.repositories;

import com.ewing.capstoneproj.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    @Query("SELECT u from Food u WHERE u.name = ?1")
        //finds food by name
    Food findFoodByName(String name);
}
