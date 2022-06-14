package com.ewing.capstoneproj.repositories;

import com.ewing.capstoneproj.UserFoodID;
import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.models.User_Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserFoodRepository extends JpaRepository<User_Food, UserFoodID> {
    @Query("SELECT u from User_Food u WHERE u.user_id = ?1")
    List<User_Food> findUserFoodById(Integer user_id);

    @Query("select u from User_Food u where u.user_id = ?1 and u.date = ?2")
    List<User_Food> findUserFoodByDate(Integer user_id, Date date);

}
