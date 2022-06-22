package com.ewing.capstoneproj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String viewaboutPage(Model model) {
        return "about";
    } //view about page
}