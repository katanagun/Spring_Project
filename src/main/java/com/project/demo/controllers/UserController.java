package com.project.demo.controllers;

import com.project.demo.services.ModelUserService;
import com.project.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ModelUserService userService;

    public UserController(ModelUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/exists/{userId}")
    public boolean userExists(@PathVariable Long userId) {
        return userService.existUser(userId);
    }

    @PostMapping("/insert/{userId}/{userName}")
    public void insertUser(@PathVariable Long userId, @PathVariable String userName) {
        userService.insertUser(userId, userName);
    }
}