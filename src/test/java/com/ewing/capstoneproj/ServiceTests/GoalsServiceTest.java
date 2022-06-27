package com.ewing.capstoneproj.ServiceTests;

import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.Goals;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.service.AppService;
import com.ewing.capstoneproj.service.GoalsService;
import com.ewing.capstoneproj.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class GoalsServiceTest {
    @Autowired
    GoalsService goalservice;

    @Autowired
    UserService userservice;


    @Test
    public void savegoalTest(){
        User user = new User();
        user.setEmail("testsuiteemail@gmail.com");
        user.setPassword("password1");
        user.setFirstname("Aaron");
        user.setLastname("Ewing");
        userservice.save(user);
        User testuser = userservice.findByEmail("testsuiteemail@gmail.com");
        goalservice.saveGoals(testuser,"Junit Test Goal",false,new Date());
        List<Goals> list = goalservice.incompleteGoalsList(testuser.getId());
        Goals goal = list.get(0);
        Assert.assertEquals("Junit Test Goal",goal.getText());

    }

}
