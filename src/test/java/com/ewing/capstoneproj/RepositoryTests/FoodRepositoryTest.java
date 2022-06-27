package com.ewing.capstoneproj.RepositoryTests;

import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
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
public class FoodRepositoryTest {
    //Food Repository tests, tests the query findfoodbyname
    @Autowired
    FoodRepository repo;

    @Test
    public void testFindFoodByName(){
        Food food = new Food();
        food.setName("Carrots");
        repo.save(food);
        Food testfood = repo.findFoodByName("Carrots");
        Assert.assertEquals("Carrots",testfood.getName());


    }
}
