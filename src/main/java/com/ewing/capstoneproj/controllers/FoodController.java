package com.ewing.capstoneproj.controllers;


import com.ewing.capstoneproj.exceptions.ExistingFoodException;
import com.ewing.capstoneproj.exceptions.ExistingWorkoutException;
import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.models.User_Food;
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
public class FoodController {
    @Autowired
    private UserService service;
    @Autowired
    private AppService service1;

    @GetMapping(value = {"/addfood","/foodfail"}) //mapping the addfood pages/addfood error page
    public String viewAddfoodPage(Model model) {
        List<Food> foods = service1.GetAllFoods(); //gets all the foods to list them for the add food dropdown menu
        model.addAttribute("allfoods", foods); //model for thymeleaf
        return "addFood";
    }


    @PostMapping("/process_food")
    public String foodProcess(@RequestParam(value = "foodname") String foodname, //requesting parameters from thymeleaf form
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "calories",defaultValue = "0") Integer calories,
                              @RequestParam(value = "servings",defaultValue = "0") Integer servings) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //formatting date
        Date parseddate = format.parse(date); //parsing string to get a date
        User user = service.getLoggedUser(); //get logged in user
        Food food = new Food();
        food.setName(foodname);
        if (service1.getUserFoodByKeys(user,parseddate,food) != null) throw new ExistingFoodException(); //throws exception if a food exists for that date
        service1.saveUserFood(user, food, parseddate, calories, servings); //saves userfood
        return "redirect:/viewfood";

    }

    @GetMapping("/viewfood")
    public String viewfoodPage(Model model) { //viewfood model
        User user = service.getLoggedUser(); //get logged user
        List<User_Food> userfoods = service1.ListUserFood(user); //lists food by user
        model.addAttribute("userfood", userfoods); //
        String date = ""; //setting date value to empty string, will put MM-DD-YYYY as the value
        model.addAttribute("datevalue", date);
        return "viewFood";
    }

    @PostMapping("/viewfood")
    public String viewfoodPost(Model model, @RequestParam(value = "date1") String date) throws ParseException {
        User user = service.getLoggedUser(); //get logged user
        if (date.equals("")) { //if date param is an empty string it means to reset the date, displays all user foods
            model.addAttribute("datevalue", date);
            List<User_Food> userfoods = service1.ListUserFood(user);
            model.addAttribute("userfood", userfoods);
            return "viewFood";
        } else { //takes the date string and displays those userfoods filtered by date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parseddate = format.parse(date);
            List<User_Food> userfoods = service1.FindByDateId(user, parseddate);
            model.addAttribute("userfood", userfoods);
            model.addAttribute("datevalue", date);
            return "viewFood";
        }
    }

    @GetMapping("/deletefood/{userid}/{date}/{foodid}") //mapping to delete food
    public String deleteEmployee(@PathVariable(value = "date") String date,
                                 @PathVariable(value = "foodid") Integer food_id,
                                 @PathVariable(value = "userid") Integer user_id) throws ParseException {
        User checkLogged = service.getLoggedUser(); //gets logged user
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date); //gets date from string
        User user = service.findById(user_id); //get user from the id
        Food food = service1.findByFoodId(food_id); //get the food from the id
        if (checkLogged.getId() == user_id) { //checks if the logged in user is the same as the user_id given by thymeleaf. if it matches it allows the delete
            service1.DeleteByKeys(user, parseddate, food); //carries out the delete
            return "redirect:/viewfood"; //redirects back to view food
        }
        return "/error/400"; //illegal request if the logged id does not equal the given user id
    }
    @GetMapping("/upfood/{userid}/{date}/{foodid}")//mapping to update food
    public String upFoodform(@PathVariable(value = "date") String date,
                             @PathVariable(value = "foodid") Integer foodid,
                             @PathVariable(value = "userid") Integer user_id, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        User checkLogged = service.getLoggedUser(); //gets logged user
        Date parseddate = format.parse(date); //gets date from string
        User user = service.findById(user_id); //get user from the id
        Food food = service1.findByFoodId(foodid); //get the food from the id
        if (checkLogged.getId() == user_id) { //checks if the logged in user is the same as the user_id given by thymeleaf. if it matches it allows the update
            User_Food update = service1.getUserFoodByKeys(user, parseddate, food); //sends this model to the update food form
            model.addAttribute("food", update);
            return "UpdateFood"; //redirects to update food form
        }
        return "/error/400";
    }

    @PostMapping("/foodupdate")
    public String foodupdateprocess(@ModelAttribute("food") User_Food update){ //post mapping carries out the food update
        service1.updateUserFood(update); //update the food
        return "redirect:/viewfood"; //redirect to viewfood
    }


}

