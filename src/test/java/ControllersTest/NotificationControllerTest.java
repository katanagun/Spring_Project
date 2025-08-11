package ControllersTest;

import com.project.demo.controllers.NotificationController;
import com.project.demo.db.Notification;
import com.project.demo.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    public void testGetAllNotifications() {
        List<Notification> mockNotifications = List.of(
                new Notification(1L, 100L, 200L, "notification1"),
                new Notification(2L, 101L, 201L, "notification2")
        );

        when(notificationService.getAllNotifications()).thenReturn(mockNotifications);

        var result = notificationController.getAllNotifications();

        assertEquals(2, result.size());
        assertEquals("notification1", result.iterator().next().getNotificationValue());
    }

    @Test
    public void testGetUserNotifications() {
        List<Notification> userNotifications = List.of(
                new Notification(1L, 100L, 200L, "notification1")
        );

        when(notificationService.getUserNotifications(100L)).thenReturn(userNotifications);

        var result = notificationController.getUserNotifications(100L);

        assertEquals(1, result.size());
        assertEquals("notification1", result.iterator().next().getNotificationValue());
    }
}
