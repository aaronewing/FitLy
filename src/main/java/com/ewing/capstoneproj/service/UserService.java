package com.ewing.capstoneproj.service;

import com.ewing.capstoneproj.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email); //find by email
    User save(User user); //save

    User getLoggedUser(); //get logged user

    User findById(Integer id); //find by id
}
