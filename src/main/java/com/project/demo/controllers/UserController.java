package com.project.demo.controllers;

import com.project.demo.services.ModelUserService;
import com.project.demo.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserController")
public class UserController {
    private final ModelUserService mapService;

    public UserController(ModelUserService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/{idUser}")
    public boolean get(@PathVariable long idUser){
        return mapService.getUser(idUser);
    }

    @PostMapping()
    public void post(@RequestBody User user){
        mapService.putUser(user);
    }
}