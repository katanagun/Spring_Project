package com.project.demo.services;

import com.project.demo.db.repository.UserRep;
import com.project.demo.db.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRep userRep;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldPutUser() {
        User user = new User();
        user.setName("Bogdan");

        userService.putUser(user);

        verify(userRep).save(user);
    }

    @Test
    void shouldGetUser() {
        long userId = 2;
        User mockUser = new User();
        mockUser.setName("Bogdan");

        when(userRep.findById(userId)).thenReturn(Optional.of(mockUser));

        User retrievedUser = userService.getUser(userId);

        assertNotNull(retrievedUser);
        assertEquals("Bogdan", retrievedUser.getName());
        verify(userRep).findById(userId);
    }

    @Test
    void shouldGetNullUserMissing() {
        long userId = 1;

        when(userRep.findById(userId)).thenReturn(Optional.empty());

        assertNull(userService.getUser(userId));
        verify(userRep).findById(userId);
    }
}