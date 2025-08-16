package ControllersTest;

import com.project.demo.controllers.NotificationController;
import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.NotificationRepository;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private TaskRepository taskRepository;

    private NotificationService notificationService;

    private NotificationController notificationController;

    @BeforeEach
    public void setup() {
        notificationService = new NotificationService(notificationRepository, taskRepository);
        notificationController = new NotificationController(notificationService);
    }

    @Test
    public void testGetAllNotifications() {
        List<Notification> mockNotifications = List.of(
                new Notification(1L, 100L, 200L, "notification1"),
                new Notification(2L, 101L, 201L, "notification2")
        );

        when(notificationRepository.findAll()).thenReturn(mockNotifications);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());

        List<Task> mockTasks = List.of(
                new Task(200L, 100L, "Task A", now.minusDays(1), now.plusDays(1)),
                new Task(201L, 101L, "Task B", now.minusDays(2), now.plusDays(2))
        );

        when(taskRepository.findAllAndDeletedFalse()).thenReturn(mockTasks);

        var result = notificationController.getAllNotifications();

        assertEquals(2, result.size());
        assertEquals("notification1", result.iterator().next().getNotificationValue());
    }

    @Test
    public void testGetUserNotifications() {
        List<Notification> userNotifications = List.of(
                new Notification(1L, 100L, 200L, "notification1")
        );

        when(notificationRepository.findByUserId(100L)).thenReturn(userNotifications);

        var result = notificationController.getUserNotifications(100L);

        assertEquals(1, result.size());
        assertEquals("notification1", result.iterator().next().getNotificationValue());
    }
}
