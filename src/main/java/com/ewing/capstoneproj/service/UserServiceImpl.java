package com.ewing.capstoneproj.service;

import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.repositories.UserRepository;
import com.ewing.capstoneproj.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repo;

    @Autowired
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User findByEmail(String email){
        return repo.findByEmail(email);
    }

    public User save(User user){
        User user1 = new User();
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user1);
    }

    public User getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = findByEmail(currentPrincipalName);
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user=repo.getReferenceById(id);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
