package com.project.demo.services;

import com.project.demo.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testInsertUser() {
        long idUser = 1L;
        String nameUser = "Test User";

        userService.insertUser(idUser, nameUser);

        User user = userService.users.get(idUser);

        assertNotNull(user);
        assertEquals(idUser, user.getIdUser());
        assertEquals(nameUser, user.getNameUser());
    }

    @Test
    void testExistUserWhenUserExists() {
        long idUser = 1L;
        userService.insertUser(idUser, "Existing User");

        assertTrue(userService.existUser(idUser));
    }

    @Test
    void testExistUserWhenUserDoesNotExist() {
        long idUser = 999L;

        assertFalse(userService.existUser(idUser));
    }
}
