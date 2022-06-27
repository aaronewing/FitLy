package com.ewing.capstoneproj.RepositoryTests;

import com.ewing.capstoneproj.models.*;
import com.ewing.capstoneproj.repositories.*;
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
public class UserFoodRepositoryTest {
    //User_Food repository tests, tests the queries FindUserFoodByID, findUserFoodbyDate, deleteUserFoodByKeys, getUserFoodByKeys
    @Autowired
    UserFoodRepository repo;
    @Autowired
    UserRepository userrepo;

    @Autowired
    FoodRepository foodrepo;

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
        Food food = foodrepo.findFoodByName("Chicken Wings");
        User_Food userFood = new User_Food();
        userFood.setFood_id(food.getId());
        userFood.setUser_id(testuser.getId());
        userFood.setUser(testuser);
        userFood.setFood(food);
        userFood.setCalories(100);
        userFood.setServings(100);
        userFood.setDate(date);
        repo.save(userFood);
    }
    @Test
    public void testFindUserFoodByID(){
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<User_Food>list = repo.findUserFoodById(testuser.getId());
        User_Food testuserfood = list.get(0);
        Assert.assertEquals("Chicken Wings",testuserfood.getFood().getName());
    }

    @Test
    public void testFindUserFoodByDate(){
        Date date = new Date();
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<User_Food>list = repo.findUserFoodByDate(testuser.getId(),date);
        User_Food testuserfood = list.get(0);
        Assert.assertEquals("Chicken Wings",testuserfood.getFood().getName());
    }

    @Test
    public void testDeleteUserByKeys(){
        Date date = new Date();
        Food food = foodrepo.findFoodByName("Chicken Wings");
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        User_Food testuserfood = repo.getUserFoodByKeys(testuser.getId(),date,food.getId());
        Assert.assertEquals("Chicken Wings",testuserfood.getFood().getName());
        repo.deleteUserFoodByKeys(testuserfood.getUser_id(),date,testuserfood.getFood_id());
        Assert.assertEquals(null,repo.getUserFoodByKeys(testuser.getId(),date,food.getId()));
    }

    @Test
    public void testGetUserExerciseByKeys(){
        Date date = new Date();
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        Food food = foodrepo.findFoodByName("Chicken Wings");
        User_Food testuserfood = repo.getUserFoodByKeys(testuser.getId(),date,food.getId());
        Assert.assertEquals("Chicken Wings",testuserfood.getFood().getName());
    }
}
