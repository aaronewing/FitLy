package com.ewing.capstoneproj.repositories;


import com.ewing.capstoneproj.UserExerciseID;
import com.ewing.capstoneproj.models.User_Exercises;
import com.ewing.capstoneproj.models.User_Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UserExerciseRepository extends JpaRepository<User_Exercises, UserExerciseID> {
    @Query("SELECT u from User_Exercises u WHERE u.user_id = ?1")
    List<User_Exercises> findUserExerciseById(Integer user_id);

    @Query("select u from User_Exercises u where u.user_id = ?1 and u.date = ?2")
    List<User_Exercises> findUserExerciseByDate(Integer user_id, Date date);

    @Transactional
    @Modifying
    @Query("DELETE FROM User_Exercises u where u.user_id = ?1 and u.date = ?2 and u.exercise_id = ?3 ")
    void deleteUserExerciseByKeys(Integer user_id, Date date, Integer exercise_id);
}
