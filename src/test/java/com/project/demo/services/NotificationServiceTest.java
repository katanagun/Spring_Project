package com.project.demo.services;

import com.project.demo.models.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {

    private NotificationService notificationService;

    @BeforeEach
    public void setup(){
        notificationService = new NotificationService();
    }

    @Test
    public void shouldPutUserNotificationMap(){
        Notification notification1 = new Notification();
        notification1.setIdNotification(1);
        notification1.setValueNotification("notification for user");
        notificationService.putNotification(notification1, "user");

        assertNotNull(notificationService.getNotification(1, "user"));
        assertEquals("notification for user", notificationService.getNotification(1, "user").getValueNotification());
    }

    @Test
    public void shouldPutTaskNotificationMap(){
        Notification notification2 = new Notification();
        notification2.setIdNotification(2);
        notification2.setValueNotification("notification for task");
        notificationService.putNotification(notification2, "task");

        assertNotNull(notificationService.getNotification(2, "task"));
        assertEquals("notification for task", notificationService.getNotification(2, "task").getValueNotification());
    }

    @Test
    public void shouldGetUserNotification(){
        Notification notification = new Notification();
        notification.setIdNotification(1);
        notification.setValueNotification("notification for user");
        notificationService.putNotification(notification, "user");

        Notification gNotification = notificationService.getNotification(1, "user");

        assertNotNull(notificationService.getNotification(1, "user"));
        assertEquals("notification for user", gNotification.getValueNotification());
    }

    @Test
    public void shouldGetTaskNotification(){
        Notification notification = new Notification();
        notification.setIdNotification(2);
        notification.setValueNotification("notification for task");
        notificationService.putNotification(notification, "task");

        Notification gNotification = notificationService.getNotification(2, "task");

        assertNotNull(notificationService.getNotification(2, "task"));
        assertEquals("notification for task", gNotification.getValueNotification());
    }

    @Test
    public void shouldGetNullNotificationUserMissing(){
        assertNull(notificationService.getNotification(10, "user"));
    }

    @Test
    public void shouldGetNullNotificationTaskMissing(){
        assertNull(notificationService.getNotification(10, "task"));
    }
}
