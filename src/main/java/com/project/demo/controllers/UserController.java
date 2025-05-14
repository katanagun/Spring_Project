package com.project.demo.controllers;

import com.project.demo.services.ModelUserService;
import com.project.demo.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UserController")
public class UserController {
    private final ModelUserService modelUserService;

    public UserController(ModelUserService modelUserService) {
        this.modelUserService = modelUserService;
    }

    @GetMapping("/{idUser}")
    public boolean get(@PathVariable long idUser){
        return modelUserService.existUser(idUser);
    }

    @PostMapping("/{idUser}/{nameUser}")
    public void post(@PathVariable long idUser, @PathVariable String nameUser){
        modelUserService.insertUser(idUser, nameUser);
    }
}
