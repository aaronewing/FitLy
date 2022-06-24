package com.ewing.capstoneproj.controllers;

import com.ewing.capstoneproj.service.GoalsService;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    GoalsService service;

    @Autowired
    UserService userService;


    @GetMapping("/home")
    public String viewPage(Model model){
        return "home";
    }
}


