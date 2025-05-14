package com.project.demo.controllers;

import com.project.demo.services.ModelNotificationService;
import com.project.demo.models.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/NotificationController")
public class NotificationController {
    private final ModelNotificationService mapService;

    public NotificationController(ModelNotificationService mapService){
        this.mapService = mapService;
    }

    @GetMapping("/{idUser}")
    public Collection<Notification> getUser(@PathVariable long idUser){
        return mapService.getUserNotifications(idUser);
    }

    @GetMapping("/pending")
    public Collection<Notification> get(){
        return mapService.getNotification();
    }

}