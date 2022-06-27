package com.ewing.capstoneproj.repositories;


import com.ewing.capstoneproj.UserExerciseID;
import com.ewing.capstoneproj.models.User_Exercises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Repository
public interface UserExerciseRepository extends JpaRepository<User_Exercises, UserExerciseID> {
    @Query("SELECT u from User_Exercises u WHERE u.user_id = ?1") //finds user exercises by user
    List<User_Exercises> findUserExerciseById(Integer user_id);

    @Query("select u from User_Exercises u where u.user_id = ?1 and u.date = ?2") //finds user exercises by user and date
    List<User_Exercises> findUserExerciseByDate(Integer user_id, Date date);

    @Transactional
    @Modifying
    @Query("DELETE FROM User_Exercises u where u.user_id = ?1 and u.date = ?2 and u.exercise_id = ?3 ") //deletes user exercise by user date and exercise id
    void deleteUserExerciseByKeys(Integer user_id, Date date, Integer exercise_id);

    @Query("SELECT u FROM User_Exercises u where u.user_id = ?1 and u.date = ?2 and u.exercise_id = ?3") //selects user exercise by user date and exercise id
    User_Exercises getUserExerciseByKeys(Integer user_id, Date date, Integer exercise_id);
}
