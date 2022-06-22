package com.ewing.capstoneproj.controllers;


import com.ewing.capstoneproj.models.Goals;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.models.User_Exercises;
import com.ewing.capstoneproj.models.User_Food;
import com.ewing.capstoneproj.service.GoalsService;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
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

    @GetMapping("/changegoal/{goalid}")
    public String changeComplete(@PathVariable(value = "goalid") Integer goal_id, Model model){
        Goals goals = goalsService.getGoalbyid(goal_id);
        goals.setCompleted(true);
        goalsService.updateGoal(goals);
        return "redirect:/viewgoals";
    }

    @GetMapping("/deletegoal/{goalid}")
    public String deleteGoal(@PathVariable(value = "goalid") Integer goal_id){
        goalsService.deleteGoal(goal_id);
        return "redirect:/viewgoals";
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

    @PostMapping("/viewgoals")
    public String viewgoalsPost(Model model, @RequestParam(value = "incompletedate") String date) throws ParseException {
        User user = userService.getLoggedUser();
        if (date.equals("")) {
            model.addAttribute("datevalue", date);
            List<Goals> incomplete = goalsService.incompleteGoalsList(user.getId());
            model.addAttribute("incomplete", incomplete);
            List<Goals> complete = goalsService.completeGoalsList(user.getId());
            model.addAttribute("complete", complete);
            return "viewGoals";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parseddate = format.parse(date);
            List<Goals> incomplete = goalsService.getIncompleteGoalsbyDate(user.getId(),parseddate);
            model.addAttribute("incomplete", incomplete);
            List<Goals> complete = goalsService.completeGoalsList(user.getId());
            model.addAttribute("complete", complete);
            model.addAttribute("datevalue", date);
            return "viewGoals";
        }
    }
}
