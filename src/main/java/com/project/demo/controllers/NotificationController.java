package com.project.demo.controllers;

import com.project.demo.services.ModelNotificationService;
import com.project.demo.models.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NotificationController")
public class NotificationController {
    private final ModelNotificationService listService;

    public NotificationController(ModelNotificationService listService){
        this.listService = listService;
    }

    @GetMapping("/{idNotification}/{purpose}")
    public List<Notification> get(@PathVariable long idNotification,@PathVariable String purpose){
        return listService.getNotification(idNotification, purpose);
    }

    @PostMapping()
    public void post(@RequestBody Notification notification){
        listService.putNotification(notification);
    }
}
