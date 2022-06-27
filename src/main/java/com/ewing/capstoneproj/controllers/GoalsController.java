package com.ewing.capstoneproj.controllers;


import com.ewing.capstoneproj.models.Goals;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.service.GoalsService;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GoalsController {
    @Autowired
    UserService userService;

    @Autowired
    GoalsService goalsService;


    @GetMapping("/viewgoals")
    public String viewgoalsPage(Model model) { //view goals mapping
        User user = userService.getLoggedUser(); //gets the logged in user
        List<Goals> incomplete = goalsService.incompleteGoalsList(user.getId()); //gets their incomplete goals list
        List<Goals> complete = goalsService.completeGoalsList(user.getId()); //gets their completed goals list
        model.addAttribute("incomplete", incomplete);
        model.addAttribute("complete", complete);
        return "viewGoals";
    }

    @GetMapping("/changegoal/{goalid}/{userid}") //changes the goal to completed
    public String changeComplete(@PathVariable(value = "goalid") Integer goal_id, //goal
                                 @PathVariable(value = "userid") Integer user_id){
        Goals goals = goalsService.getGoalbyid(goal_id); //gets goal
        goals.setCompleted(true); //sets the goal to completed
        User checkLogged = userService.getLoggedUser(); //checks the logged in user
        if(checkLogged.getId() == user_id) { //if user matches, allow the update
            goalsService.updateGoal(goals);
            return "redirect:/viewgoals"; //redirect to viewgoals
        }
        return "/error/400"; //return an error otherwise
    }

    @GetMapping("/change/{goalid}/{userid}") //changes the goal to incomplete
    public String changeIncomplete(@PathVariable(value = "goalid") Integer goal_id, //goal
                                 @PathVariable(value = "userid") Integer user_id){
        Goals goals = goalsService.getGoalbyid(goal_id); //gets goal
        User checkLogged = userService.getLoggedUser(); //checks the logged in user
        if(checkLogged.getId() == user_id) { //if user matches, allow the update
            goalsService.makeIncompleteGoal(goals); //make goal incomplete
            return "redirect:/viewgoals"; //redirect to viewgoals
        }
        return "/error/400"; //return an error otherwise
    }

    @GetMapping("/deletegoal/{goalid}/{userid}") //deletes goal
    public String deleteGoal(@PathVariable(value = "goalid") Integer goal_id,
                             @PathVariable(value = "userid")Integer user_id){
        User checkLogged = userService.getLoggedUser();
        if(checkLogged.getId() == user_id) { //if user matches user id delete the goal
            goalsService.deleteGoal(goal_id);
            return "redirect:/viewgoals";
        }
        return "/error/400"; //return an error otherwise
    }

    @GetMapping("/addgoal") //adds a goal
    public String addGoal(){
        return "addGoals"; //returns add goal form
    }

    @PostMapping("/process_goal") //processes the goal
    public String processGoal(@RequestParam(value = "goaltext") String goaltext){
        User user = userService.getLoggedUser(); //gets the logged user
        Date date = new Date(); //gets the current date
        goalsService.saveGoals(user,goaltext,false,date); //saves the goal
        return "redirect:/viewgoals"; //redirects to view goals
    }
}
