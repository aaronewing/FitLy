package com.ewing.capstoneproj.service;

import com.ewing.capstoneproj.models.Goals;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.repositories.GoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoalsService {
    @Autowired
    UserService userserv;

    @Autowired
    GoalsRepository repo;


    public Goals saveGoals(User user, String text, boolean completed, Date date){ //saves goals to the database
        Goals goal = new Goals();
        goal.setUser(user);
        goal.setUser_id(user.getId());
        goal.setText(text);
        goal.setCompleted(completed);
        goal.setDate(date);
        return repo.save(goal);
    }

    public List<Goals> incompleteGoalsList(Integer user_id){//gets the list of incomplete goals by user
        return repo.GetIncompleteGoals(user_id);
    }

    public List<Goals> completeGoalsList(Integer user_id){ //gets the list of complete goals by user
        return repo.GetCompletedGoals(user_id);
    }

    public Goals getGoalbyid(Integer goal_id){ //gets a goal by id
        Goals goal = repo.getGoalsByGoal_id(goal_id);
        return goal;
    }

    public void deleteGoal(Integer goal_id){ //deletes a goal by id
        repo.deleteGoalsByGoal_id(goal_id);
    }

    public Goals updateGoal(Goals goal){ //updates a goal
        Date date = new Date();
        goal.setCompletedate(date);
        return repo.save(goal);
    }

    public Goals makeIncompleteGoal(Goals goal){ //marks a goal as incomplete again
        goal.setCompleted(false);
        return repo.save(goal);
    }
}
