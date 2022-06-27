package com.ewing.capstoneproj.RepositoryTests;

import com.ewing.capstoneproj.models.Exercises;
import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.repositories.ExerciseRepository;
import com.ewing.capstoneproj.repositories.FoodRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class ExerciseRepositoryTest {
    @Autowired
    ExerciseRepository repo;

    @Test
    public void testFindExerciseByName(){
        Exercises exercises = new Exercises();
        exercises.setName("Dumbell row");
        repo.save(exercises);
        Exercises testexercise = repo.findExerciseByName("Dumbell row");
        Assert.assertEquals("Dumbell row",testexercise.getName());

    }
}
