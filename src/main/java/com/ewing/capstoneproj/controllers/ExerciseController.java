package com.ewing.capstoneproj.controllers;

import com.ewing.capstoneproj.models.*;
import com.ewing.capstoneproj.repositories.ExerciseRepository;
import com.ewing.capstoneproj.service.AppService;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExerciseController {
    @Autowired
    private UserService service;
    @Autowired
    private AppService service1;

    @GetMapping("/addexe")
    public String viewAddexePage(Model model) {
        List<Exercises> exercises = service1.GetAllExercises();
        model.addAttribute("allexes", exercises);
        return "addExercises";
    }

    @PostMapping("/process_exe")
    public String exeProcess(@RequestParam(value = "exename") String exename,
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "weight") Integer weight,
                              @RequestParam(value = "sets") Integer sets,
                              @RequestParam(value = "reps") Integer reps) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date);
        User user = service.getLoggedUser();
        Exercises exercises = new Exercises();
        exercises.setName(exename);
        User_Exercises userexe = new User_Exercises();
        service1.saveUserExercises(user, exercises, parseddate,weight, sets, reps);
        return "redirect:addexe";

    }
    @GetMapping("/viewexe")
    public String viewfoodPage(Model model) {
        User user = service.getLoggedUser();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datestring = format.format(date);
        model.addAttribute("datevalue", datestring);
        List<User_Exercises> userexercises = service1.FindByExeDateId(user,date);
        model.addAttribute("userexes", userexercises);
        return "viewExercises";
    }

    @PostMapping("/viewexe")
    public String viewexePost(Model model, @RequestParam(value = "date2") String date) throws ParseException {
        User user = service.getLoggedUser();
        if (date.equals("")) {
            model.addAttribute("datevalue", date);
            List<User_Exercises> userexercises = service1.ListUserExercise(user);
            model.addAttribute("userexes", userexercises);
            return "viewExercises";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parseddate = format.parse(date);
            List<User_Exercises> userexercises = service1.FindByExeDateId(user, parseddate);
            model.addAttribute("userexes", userexercises);
            model.addAttribute("datevalue", date);
            return "viewExercises";
        }
    }

    @GetMapping("/delexe/{userid}/{date}/{exeid}")
    public String deleteEmployee(@PathVariable(value = "date") String date,
                                 @PathVariable(value = "exeid") Integer exe_id,
                                 @PathVariable(value = "userid") Integer user_id) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date);
        User user = service.findById(user_id);
        Exercises exercises = service1.findexeByID(exe_id);
        service1.exeDeleteByKeys(user,parseddate,exercises);
        return "redirect:/viewexe";
    }
}
