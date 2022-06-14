package com.ewing.capstoneproj.controllers;


import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.models.User_Food;
import com.ewing.capstoneproj.service.AppService;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/addfood")
    public String viewAddfoodPage(Model model) {
        List<Food> foods = service1.GetAllFoods();
        model.addAttribute("allfoods", foods);
        return "food";
    }


    @PostMapping("/process_food")
    public String foodProcess(@RequestParam(value = "foodname") String foodname,
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "calories") Integer calories,
                              @RequestParam(value = "servings") Integer servings) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = format.parse(date);
        User user = service.getLoggedUser();
        Food food = new Food();
        food.setName(foodname);
        User_Food food2 = new User_Food();
        service1.saveUserFood(user, food, parseddate, calories, servings);
        return "redirect:addfood";

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

}

