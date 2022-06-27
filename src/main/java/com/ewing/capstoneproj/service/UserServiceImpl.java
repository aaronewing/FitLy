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

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repo;

    @Autowired
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User findByEmail(String email) { //finds a user by email
        return repo.findByEmail(email);
    }

    public User save(User user) { //saves a user
        User user1 = new User();
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPassword(encoder.encode(user.getPassword())); //encrypts the password
        return repo.save(user1); //saves the user
    }

    public User getLoggedUser() { //gets the logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = findByEmail(currentPrincipalName);
        return user;
    }

    @Override
    public User findById(Integer id) { //finds a user by id
        User user = repo.getReferenceById(id);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //leftover from custom user details
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
