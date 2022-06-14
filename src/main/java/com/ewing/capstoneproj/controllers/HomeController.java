package com.ewing.capstoneproj.controllers;

import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.repositories.FoodRepository;
import com.ewing.capstoneproj.repositories.UserFoodRepository;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @Autowired
    UserService userserv;
    @Autowired
    FoodRepository repo;
    @Autowired
    UserFoodRepository userfoodrepo;

    @GetMapping("/home")
    public String viewPage(Model model){
        User user = userserv.getLoggedUser();
        return "home";
    }
}


