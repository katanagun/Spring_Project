package com.project.demo.controllers;

import com.project.demo.services.ModelNotificationService;
import com.project.demo.models.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NotificationController")
public class NotificationController {
    private final ModelNotificationService mapService;

    public NotificationController(ModelNotificationService listService){
        this.mapService = listService;
    }

    @GetMapping("/user/{idNotification}")
    public Notification getUser(@PathVariable long idNotification){
        return mapService.getNotification(idNotification, "user");
    }

    @GetMapping("/task/{idNotification}")
    public Notification getTask(@PathVariable long idNotification){
        return mapService.getNotification(idNotification, "task");
    }

    @PostMapping("/user")
    public void postUser(@RequestBody Notification notification){
        mapService.putNotification(notification, "user");
    }

    @PostMapping("/task")
    public void postTask(@RequestBody Notification notification){
        mapService.putNotification(notification, "task");
    }
}
