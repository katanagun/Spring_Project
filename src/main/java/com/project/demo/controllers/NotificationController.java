package com.project.demo.controllers;

import com.project.demo.services.ModelNotificationService;
import com.project.demo.models.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/NotificationController")
public class NotificationController {
    private final ModelNotificationService modelNotificationService;

    public NotificationController(ModelNotificationService modelNotificationService){
        this.modelNotificationService = modelNotificationService;
    }

    @GetMapping("/{idUser}")
    public Collection<Notification> getUser(@PathVariable long idUser){
        return modelNotificationService.getUserNotifications(idUser);
    }

    @GetMapping("/pending")
    public Collection<Notification> get(){
        return modelNotificationService.getAllNotifications();
    }

}
