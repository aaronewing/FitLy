package com.ewing.capstoneproj.ServiceTests;

import com.ewing.capstoneproj.models.User;
import com.ewing.capstoneproj.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserServiceTest {
    @Autowired
    UserService service;


    @Test
    public void testFindUserByEmail(){
        User user = new User();
        user.setEmail("testsuiteemail@gmail.com");
        user.setPassword("password1");
        user.setFirstname("Aaron");
        user.setLastname("Ewing");
        service.save(user);
        User testuser = service.findByEmail("testsuiteemail@gmail.com");
        Assert.assertEquals("Ewing",testuser.getLastname());
    }



}
