package com.ewing.capstoneproj.repositories;

import com.ewing.capstoneproj.models.Exercises;
import com.ewing.capstoneproj.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercises, Integer> {
    @Query("SELECT u from Exercises u WHERE u.name = ?1")
        //finds exercises by name
    Exercises findExerciseByName(String name);
}
