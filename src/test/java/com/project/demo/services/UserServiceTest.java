package com.project.demo.services;

import com.project.demo.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setup(){
        userService = new UserService();
    }

    @Test
    public void shouldPutUserMap(){
        User user = new User(1, "Bogdan");
        userService.putUser(user);

        assertNotNull(userService.getUser(1));
        assertEquals("Bogdan", userService.getUser(1).getNameUser());
    }

    @Test
    public void shouldGetUserMap(){
        User user = new User(2, "Bogdan");
        userService.putUser(user);

        User gUser = userService.getUser(2);

        assertNotNull(userService.getUser(2));
        assertEquals("Bogdan", userService.getUser(2).getNameUser());
    }

    @Test
    public void shouldGetNullUserMissing(){
        assertNull(userService.getUser(1));
    }
}
