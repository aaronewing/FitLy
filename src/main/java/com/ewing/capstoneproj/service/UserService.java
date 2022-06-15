package com.ewing.capstoneproj.service;

import com.ewing.capstoneproj.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(User user);

    User getLoggedUser();

    User findById(Integer id);
}
