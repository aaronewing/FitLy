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
    public String viewgoalsPage(Model model) {
        User user = userService.getLoggedUser();
        List<Goals> incomplete = goalsService.incompleteGoalsList(user.getId());
        List<Goals> complete = goalsService.completeGoalsList(user.getId());
        model.addAttribute("incomplete", incomplete);
        model.addAttribute("complete", complete);
        return "viewGoals";
    }

    @GetMapping("/changegoal/{goalid}/{userid}")
    public String changeComplete(@PathVariable(value = "goalid") Integer goal_id,
                                 @PathVariable(value = "userid") Integer user_id, Model model){
        Goals goals = goalsService.getGoalbyid(goal_id);
        goals.setCompleted(true);
        User checkLogged = userService.getLoggedUser();
        if(checkLogged.getId() == user_id) {
            goalsService.updateGoal(goals);
            return "redirect:/viewgoals";
        }
        return "/error/400";
    }

    @GetMapping("/deletegoal/{goalid}/{userid}")
    public String deleteGoal(@PathVariable(value = "goalid") Integer goal_id,
                             @PathVariable(value = "userid")Integer user_id){
        User checkLogged = userService.getLoggedUser();
        if(checkLogged.getId() == user_id) {
            goalsService.deleteGoal(goal_id);
            return "redirect:/viewgoals";
        }
        return "/error/400";
    }

    @GetMapping("/addgoal")
    public String addGoal(){
        return "addGoals";
    }

    @PostMapping("/process_goal")
    public String processGoal(@RequestParam(value = "goaltext") String goaltext){
        User user = userService.getLoggedUser();
        Date date = new Date();
        goalsService.saveGoals(user,goaltext,false,date);
        return "redirect:/viewgoals";
    }
}
