package com.project.demo.services;

import com.project.demo.db.repository.NotificationRep;
import com.project.demo.db.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRep notificationRep;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldPutUserNotification() {
        Notification notification = new Notification();
        notification.setValue("notification for user");
        String type = "user";

        notificationService.putNotification(notification, type);

        verify(notificationRep).save(notification);
        assertEquals(type, notification.getType());
    }

    @Test
    void shouldPutTaskNotification() {
        Notification notification = new Notification();
        notification.setValue("notification for task");
        String type = "task";

        notificationService.putNotification(notification, type);

        verify(notificationRep).save(notification);
        assertEquals(type, notification.getType());
    }

    @Test
    void shouldGetUserNotification() {
        long notificationId = 1;
        String type = "user";
        Notification mockNotification = new Notification();
        mockNotification.setId(notificationId);
        mockNotification.setValue("notification for user");
        mockNotification.setType(type);

        when(notificationRep.findById(notificationId)).thenReturn(Optional.of(mockNotification));

        Notification retrievedNotification = notificationService.getNotification(notificationId, type);

        assertNotNull(retrievedNotification);
        assertEquals("notification for user", retrievedNotification.getValue());
        assertEquals(type, retrievedNotification.getType());
        verify(notificationRep).findById(notificationId);
    }

    @Test
    void shouldGetTaskNotification() {
        long notificationId = 2;
        String type = "task";
        Notification mockNotification = new Notification();
        mockNotification.setId(notificationId);
        mockNotification.setValue("notification for task");
        mockNotification.setType(type);

        when(notificationRep.findById(notificationId)).thenReturn(Optional.of(mockNotification));

        Notification retrievedNotification = notificationService.getNotification(notificationId, type);

        assertNotNull(retrievedNotification);
        assertEquals("notification for task", retrievedNotification.getValue());
        assertEquals(type, retrievedNotification.getType());
        verify(notificationRep).findById(notificationId);
    }

    @Test
    void shouldGetNullNotificationUserMissing() {
        long notificationId = 10;
        String type = "user";

        when(notificationRep.findById(notificationId)).thenReturn(Optional.empty());

        Notification retrievedNotification = notificationService.getNotification(notificationId, type);
        assertNull(retrievedNotification);
        verify(notificationRep).findById(notificationId);
    }

    @Test
    void shouldGetNullNotificationTaskMissing() {
        long notificationId = 10;
        String type = "task";

        when(notificationRep.findById(notificationId)).thenReturn(Optional.empty());

        Notification retrievedNotification = notificationService.getNotification(notificationId, type);
        assertNull(retrievedNotification);
        verify(notificationRep).findById(notificationId);
    }

    @Test
    void shouldReturnNullIfTypeDoesNotMatch() {
        long notificationId = 1;
        String expectedType = "user";
        String actualType = "task";

        Notification mockNotification = new Notification();
        mockNotification.setId(notificationId);
        mockNotification.setValue("test notification");
        mockNotification.setType(actualType);

        when(notificationRep.findById(notificationId)).thenReturn(Optional.of(mockNotification));

        Notification retrievedNotification = notificationService.getNotification(notificationId, expectedType);
        assertNull(retrievedNotification);
        verify(notificationRep).findById(notificationId);
    }
}