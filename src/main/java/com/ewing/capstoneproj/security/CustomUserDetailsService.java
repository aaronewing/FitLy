package com.ewing.capstoneproj.security;

import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl service;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //load by username
        User user = service.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }


}
