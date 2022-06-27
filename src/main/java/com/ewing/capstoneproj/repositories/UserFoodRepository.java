package com.ewing.capstoneproj.repositories;

import com.ewing.capstoneproj.UserFoodID;
import com.ewing.capstoneproj.models.User_Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface UserFoodRepository extends JpaRepository<User_Food, UserFoodID> {
    @Query("SELECT u from User_Food u WHERE u.user_id = ?1") //selects user foods by user
    List<User_Food> findUserFoodById(Integer user_id);

    @Query("select u from User_Food u where u.user_id = ?1 and u.date = ?2") //selects user foods by user and date
    List<User_Food> findUserFoodByDate(Integer user_id, Date date);
    @Transactional
    @Modifying
    @Query("DELETE FROM User_Food u where u.user_id = ?1 and u.date = ?2 and u.food_id = ?3 ") //delete user foods by user date and food id
    void deleteUserFoodByKeys(Integer user_id, Date date, Integer food_id);

    @Query("SELECT u FROM User_Food u where u.user_id = ?1 and u.date = ?2 and u.food_id = ?3") //gets user food by user date and food id
    User_Food getUserFoodByKeys(Integer user_id, Date date, Integer exercise_id);

}
