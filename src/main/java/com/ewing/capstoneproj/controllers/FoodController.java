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

    @GetMapping(value = {"/addfood","/foodfail"})
    public String viewAddfoodPage(Model model) {
        List<Food> foods = service1.GetAllFoods();
        model.addAttribute("allfoods", foods);
        return "addFood";
    }


    @PostMapping("/process_food")
    public String foodProcess(@RequestParam(value = "foodname") String foodname,
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "calories",defaultValue = "0") Integer calories,
                              @RequestParam(value = "servings",defaultValue = "0") Integer servings) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date);
        User user = service.getLoggedUser();
        Food food = new Food();
        food.setName(foodname);
        if (service1.getUserFoodByKeys(user,parseddate,food) != null) throw new ExistingFoodException();
        service1.saveUserFood(user, food, parseddate, calories, servings);
        return "redirect:/viewfood";

    }

    @GetMapping("/viewfood")
    public String viewfoodPage(Model model) {
        User user = service.getLoggedUser();
        List<User_Food> userfoods = service1.ListUserFood(user);
        model.addAttribute("userfood", userfoods);
        String date = "";
        model.addAttribute("datevalue", date);
        return "viewFood";
    }

    @PostMapping("/viewfood")
    public String viewfoodPost(Model model, @RequestParam(value = "date1") String date) throws ParseException {
        User user = service.getLoggedUser();
        if (date.equals("")) {
            model.addAttribute("datevalue", date);
            List<User_Food> userfoods = service1.ListUserFood(user);
            model.addAttribute("userfood", userfoods);
            return "viewFood";
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parseddate = format.parse(date);
            List<User_Food> userfoods = service1.FindByDateId(user, parseddate);
            model.addAttribute("userfood", userfoods);
            model.addAttribute("datevalue", date);
            return "viewFood";
        }
    }

    @GetMapping("/deletefood/{userid}/{date}/{foodid}")
    public String deleteEmployee(@PathVariable(value = "date") String date,
                                 @PathVariable(value = "foodid") Integer food_id,
                                 @PathVariable(value = "userid") Integer user_id) throws ParseException {
        User checkLogged = service.getLoggedUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date);
        User user = service.findById(user_id);
        Food food = service1.findByFoodId(food_id);
        if (checkLogged.getId() == user_id) {
            service1.DeleteByKeys(user, parseddate, food);
            return "redirect:/viewfood";
        }
        return "/error/400";
    }
    @GetMapping("/upfood/{userid}/{date}/{foodid}")
    public String upFoodform(@PathVariable(value = "date") String date,
                             @PathVariable(value = "foodid") Integer foodid,
                             @PathVariable(value = "userid") Integer user_id, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        User checkLogged = service.getLoggedUser();
        Date parseddate = format.parse(date);
        User user = service.findById(user_id);
        Food food = service1.findByFoodId(foodid);
        if (checkLogged.getId() == user_id) {
            User_Food update = service1.getUserFoodByKeys(user, parseddate, food);
            model.addAttribute("food", update);
            return "UpdateFood";
        }
        return "/error/400";
    }

    @PostMapping("/foodupdate")
    public String foodupdateprocess(@ModelAttribute("food") User_Food update){
        service1.updateUserFood(update);
        return "redirect:/viewfood";
    }


}

