package com.ewing.capstoneproj.controllers;

import com.ewing.capstoneproj.exceptions.RegisterFailedException;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    final String redirecthome = "redirect:home";
    final String index = "index";
    @Autowired
    private UserService service; //service to handle user requests


    @GetMapping(value = {"/", "/registerfail"})
    public String viewHomePage(Model model) {
        model.addAttribute("user", new User());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return index; //keep returning index if not logged in
        }
        return redirecthome; //return home if logged in
    }

    @PostMapping("/process_register") //register mapping
    public String processRegistration(User user) {
        if (service.findByEmail(user.getEmail()) != null) throw new RegisterFailedException();
        service.save(user); //saves user
        return "redirect:success"; //returns the success page

    }
}
