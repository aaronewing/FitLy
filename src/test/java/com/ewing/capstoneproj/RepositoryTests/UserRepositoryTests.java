package com.ewing.capstoneproj.RepositoryTests;


import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;


    @Test
    public void testFindUserByEmail(){
        User user = new User();
        user.setEmail("testsuiteemail@gmail.com");
        user.setPassword("password1");
        user.setFirstname("Aaron");
        user.setLastname("Ewing");
        userRepository.save(user);
        User testuser = userRepository.findByEmail("testsuiteemail@gmail.com");
        Assert.assertEquals("Aaron",testuser.getFirstname());

    }

}
