package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private NotificationService notificationService;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(new UserService(), new NotificationService(taskService));
        notificationService = new NotificationService(taskService);
    }

    @Test
    void testGetUserNotifications() {
        Notification notification1 = new Notification(1L, 101L, "deleted");

        notificationService.notifications.put(101L, notification1);

        Collection<Notification> userNotifications = notificationService.getUserNotifications(1L);

        assertEquals(1, userNotifications.size());
        assertTrue(userNotifications.contains(notification1));

    }

    @Test
    void testGetAllNotifications() {
        Notification notification1 = new Notification(1L, 101L, "deleted");
        Notification notification2 = new Notification(2L, 102L, "deleted");

        Task task1 = new Task(101L, 1L, "Task 1", ZonedDateTime.now().plusDays(3));
        Task task2 = new Task(102L, 2L, "Task 2", ZonedDateTime.now().minusDays(1));

        notificationService.notifications.put(101L, notification1);
        notificationService.notifications.put(102L, notification2);

        taskService.tasks.put(101L, task1);
        taskService.tasks.put(102L, task2);

        Collection<Notification> allNotifications = notificationService.getAllNotifications();

        assertEquals(1, allNotifications.size());
        assertTrue(allNotifications.contains(notification1));
        assertFalse(allNotifications.contains(notification2));
    }

    @Test
    void testGetAllNotificationsWhenNoNotifications() {
        notificationService.notifications.clear();

        Collection<Notification> allNotifications = notificationService.getAllNotifications();

        assertTrue(allNotifications.isEmpty());
    }

}
