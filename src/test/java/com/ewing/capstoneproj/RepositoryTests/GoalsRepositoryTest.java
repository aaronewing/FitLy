package com.ewing.capstoneproj.RepositoryTests;

import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.Goals;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.repositories.FoodRepository;
import com.ewing.capstoneproj.repositories.GoalsRepository;
import com.ewing.capstoneproj.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
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
public class GoalsRepositoryTest {
    //Goals repository tests, tests the queries getIncompleteTests, getCompleteTests, getGoalsByGoal_Id, deleteGoalsByGoal_Id
    @Autowired
    GoalsRepository repo;

    @Autowired
    UserRepository userrepo;

    @BeforeEach
    public void setup(){
        User user = new User();
        user.setEmail("testsuiteemail@gmail.com");
        user.setPassword("password1");
        user.setFirstname("Aaron");
        user.setLastname("Ewing");
        userrepo.save(user);
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        Goals goals = new Goals();
        Goals goals1 = new Goals();
        goals.setCompleted(false);
        goals1.setCompleted(true);
        goals.setUser(testuser);
        goals1.setUser(testuser);
        goals.setText("passed");
        goals1.setText("passed complete");
        goals.setUser_id(testuser.getId());
        goals1.setUser_id(testuser.getId());
        goals.setDate(new Date());
        goals1.setDate(new Date());
        repo.save(goals);
        repo.save(goals1);
    }
    @Test
    public void getIncompleteGoalsTest(){
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<Goals> list = repo.GetIncompleteGoals(testuser.getId());
        Goals goal = list.get(0);
        Assert.assertEquals("passed",goal.getText());
    }

    @Test
    public void getCompleteGoalsTest(){
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<Goals> list = repo.GetCompletedGoals(testuser.getId());
        Goals goal = list.get(0);
        Assert.assertEquals("passed complete",goal.getText());
    }

    @Test
    public void getGoalsByGoal_IDTest(){
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<Goals> list = repo.GetCompletedGoals(testuser.getId());
        Goals goal = list.get(0);
        Goals testgoal = repo.getGoalsByGoal_id(goal.getGoal_id());
        Assert.assertEquals("passed complete",testgoal.getText());
    }

    @Test
    public void deleteGoalsByGoal_IDTest(){
        User testuser = userrepo.findByEmail("testsuiteemail@gmail.com");
        List<Goals> list = repo.GetCompletedGoals(testuser.getId());
        Goals goal = list.get(0);
        repo.deleteGoalsByGoal_id(goal.getGoal_id());
        Assert.assertEquals(null,repo.getGoalsByGoal_id(goal.getGoal_id()));
    }
}
