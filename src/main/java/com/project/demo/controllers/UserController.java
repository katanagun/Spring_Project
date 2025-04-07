package com.project.demo.controllers;

import com.project.demo.services.ModelUserService;
import com.project.demo.db.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserController")
public class UserController {
    private final ModelUserService userService;

    public UserController(ModelUserService mapService) {
        this.userService = mapService;
    }

    @GetMapping("/{key}")
    public User get(@PathVariable long idUser){
        return userService.getUser(idUser);
    }

    @PostMapping()
    public void post(@RequestBody User user){
        userService.putUser(user);
    }
}
