package com.ewing.capstoneproj.RepositoryTests;

import com.ewing.capstoneproj.models.Exercises;
import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.models.User_Exercises;
import com.ewing.capstoneproj.repositories.ExerciseRepository;
import com.ewing.capstoneproj.repositories.FoodRepository;
import com.ewing.capstoneproj.repositories.UserExerciseRepository;
import com.ewing.capstoneproj.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class UserExercisesRepositoryTest {
    //User_Exercise repository tests, tests the queries FindUserExerciseByID, findUserExercisebyDate, deleteUserByKeys, getUserExercisesByKeys
    @Autowired
    UserExerciseRepository repo;
    @Autowired
    UserRepository userrepo;

    @Autowired
    ExerciseRepository exerepo;

    @BeforeEach
    public void setUp(){
        User user = new User();
        user.setEmail("testsuiteemail@gmail.com");
        user.setPassword("password1");
        user.setFirstname("Aaron");
        user.setLastname("Ewing");
        userrepo.save(user);
        Date date = new Date();
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        Exercises exercise = exerepo.findExerciseByName("Deadlift");
        User_Exercises exercises = new User_Exercises();
        exercises.setExercise_id(exercise.getId());
        exercises.setUser_id(testuser.getId());
        exercises.setUser(testuser);
        exercises.setExercises(exercise);
        exercises.setSets(3);
        exercises.setReps(10);
        exercises.setDate(date);
        repo.save(exercises);
    }
    @Test
    public void testFindUserExerciseByID(){
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<User_Exercises>list = repo.findUserExerciseById(testuser.getId());
        User_Exercises testuserexercise = list.get(0);
        Assert.assertEquals("Deadlift",testuserexercise.getExercises().getName());
    }

    @Test
    public void testFindUserExerciseByDate(){
        Date date = new Date();
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<User_Exercises>list = repo.findUserExerciseByDate(testuser.getId(),date);
        User_Exercises testuserexercise = list.get(0);
        Assert.assertEquals("Deadlift",testuserexercise.getExercises().getName());
    }

    @Test
    public void testDeleteUserByKeys(){
        Date date = new Date();
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        Exercises exercise = exerepo.findExerciseByName("Deadlift");
        User_Exercises testuserexercise = repo.getUserExerciseByKeys(testuser.getId(),date,exercise.getId());
        Assert.assertEquals("Deadlift",testuserexercise.getExercises().getName());
        repo.deleteUserExerciseByKeys(testuserexercise.getUser_id(),date,testuserexercise.getExercise_id());
        Assert.assertEquals(null,repo.getUserExerciseByKeys(testuser.getId(),date,exercise.getId()));
    }

    @Test
    public void testGetUserExerciseByKeys(){
        Date date = new Date();
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        Exercises exercise = exerepo.findExerciseByName("Deadlift");
        User_Exercises testuserexercise = repo.getUserExerciseByKeys(testuser.getId(),date,exercise.getId());
        Assert.assertEquals("Deadlift",testuserexercise.getExercises().getName());
    }
}
