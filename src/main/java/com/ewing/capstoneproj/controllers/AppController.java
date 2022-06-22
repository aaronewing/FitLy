package com.ewing.capstoneproj.controllers;

import com.ewing.capstoneproj.models.Food;
import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.models.User_Food;
import com.ewing.capstoneproj.repositories.UserRepository;
import com.ewing.capstoneproj.service.AppService;
import com.ewing.capstoneproj.service.UserService;
import com.ewing.capstoneproj.service.UserServiceImpl;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class AppController {
    @Autowired
    private UserService service; //service to handle user requests


    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("user", new User());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "index"; //keep returning index if not logged in
        }
        return "redirect:home"; //return home if logged in
    }

    @PostMapping("/process_register") //register mapping
    public String processRegistration(User user){
        try {
            service.save(user); //saves user
            return "redirect:success"; //returns the success page
        }catch (Exception e){
            System.out.println("catching exceptions hopefully"); //catches a failure exception for register (not working currently)
            return "redirect:/";
        }
    }

}
