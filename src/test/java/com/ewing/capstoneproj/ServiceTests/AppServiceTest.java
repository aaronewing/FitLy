package com.ewing.capstoneproj.ServiceTests;

import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.service.AppService;
import com.ewing.capstoneproj.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AppServiceTest {
    //App Service tests the method saveFood
    @Autowired
    AppService appservice;

    @Test
    public void saveFoodTest(){
        Food food = new Food();
        food.setName("Tuna Sandwich");
        appservice.savefood(food);
        Food testfood = appservice.findFoodByName(food.getName());
        Assert.assertEquals("Tuna Sandwich",testfood.getName());
    }

}
