package com.ewing.capstoneproj.controllers;

import com.ewing.capstoneproj.exceptions.ExistingWorkoutException;
import com.ewing.capstoneproj.models.*;
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
    private UserService service; //service to handle user requests
    @Autowired
    private AppService service1; //service to handle app/exercise requests



    @GetMapping(value = {"/addexe","/exefail"}) //add exercise form
    public String viewAddexePage(Model model) {
        List<Exercises> exercises = service1.GetAllExercises();
        model.addAttribute("allexes", exercises);
        return "addExercises";
    }

    @PostMapping("/process_exe") //process exercise page
    public String exeProcess(@RequestParam(value = "exename") String exename,
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "weight" ,defaultValue = "0") Integer weight,
                              @RequestParam(value = "sets", defaultValue = "0") Integer sets,
                              @RequestParam(value = "reps",defaultValue = "0") Integer reps) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //parses date
        Date parseddate = format.parse(date); //puts into new date object
        User user = service.getLoggedUser(); //gets logged in user
        Exercises exercises = new Exercises();
        exercises.setName(exename); //sets exercise name
        if (service1.getUserExeByKeys(user,parseddate,exercises) != null) throw new ExistingWorkoutException();
        service1.saveUserExercises(user, exercises, parseddate,weight, sets, reps); //saves user exercise
        return "redirect:/viewexe";

    }
    @GetMapping("/viewexe")
    public String viewexePage(Model model) {
        User user = service.getLoggedUser(); //get the logged user
        String datestring = "";
        model.addAttribute("datevalue", datestring); //passes the value for the form
        List<User_Exercises> userexercises = service1.ListUserExercise(user);//gets the exercises by todays date
        model.addAttribute("userexes", userexercises); //passes to html
        return "viewExercises";
    }

    @PostMapping("/viewexe") //this viewexe post mapping is to filter exercises by date
    public String viewexePost(Model model, @RequestParam(value = "date2") String date) throws ParseException {
        User user = service.getLoggedUser();
        if (date.equals("")) { //if the user resets date, it prints all of the exercises for that user
            model.addAttribute("datevalue", date);
            List<User_Exercises> userexercises = service1.ListUserExercise(user);
            model.addAttribute("userexes", userexercises);
            return "viewExercises";
        } else { //if the user selects a date, it prints the exercises for that user on that date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parseddate = format.parse(date);
            List<User_Exercises> userexercises = service1.FindByExeDateId(user, parseddate); //querys for exercises by that date and user
            model.addAttribute("userexes", userexercises); //sends to html
            model.addAttribute("datevalue", date); //sends datevalue to html
            return "viewExercises";
        }
    }

    @GetMapping("/delexe/{userid}/{date}/{exeid}") //getmapping for entry deletion
    public String deleteEmployee(@PathVariable(value = "date") String date,
                                 @PathVariable(value = "exeid") Integer exe_id,
                                 @PathVariable(value = "userid") Integer user_id) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date); //parses date into readable date object
        User user = service.findById(user_id); //gets user
        Exercises exercises = service1.findexeByID(exe_id); //gets id
        service1.exeDeleteByKeys(user,parseddate,exercises); //deletes by the 3 keys of the entry
        return "redirect:/viewexe";
    }

    @GetMapping("/upexe/{userid}/{date}/{exeid}") //getmapping for entry update form
    public String upExe(@PathVariable(value = "date") String date,
                                 @PathVariable(value = "exeid") Integer exe_id,
                                 @PathVariable(value = "userid") Integer user_id, Model model) throws ParseException {
        User checkUser = service.getLoggedUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        User user = service.findById(user_id);
        Exercises exercises = service1.findexeByID(exe_id);
        Date parseddate = format.parse(date);
        if (checkUser.getId() == user_id) {
            User_Exercises update = service1.getUserExeByKeys(user, parseddate, exercises); //gets entry by its keys
            model.addAttribute("exe", update); //sends that entry to form for update
            return "updateExercises";
        }
        return "/error/400";
    }

    @PostMapping("/exeupdate") //this postmapping completes the update
    public String processupdate(@ModelAttribute("exe") User_Exercises update){
        service1.updateUserExercises(update); //updates the exercise
        return "redirect:/viewexe"; //returns to the view exercises
    }
}
