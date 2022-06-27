package com.ewing.capstoneproj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpController { //sign up successful controller
    @GetMapping("/success")
    public String viewsuccPage(Model model) {
        return "signupsuccess";
    }
}
