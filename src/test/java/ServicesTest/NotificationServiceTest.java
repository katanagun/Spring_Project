package ServicesTest;

import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.NotificationRepository;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.services.ModelNotificationService;
import com.project.demo.services.ModelTaskService;
import com.project.demo.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private NotificationRepository notificationRepo;
    private TaskRepository taskRepo;
    private ModelNotificationService notificationService;
    private ModelTaskService taskService;

    @BeforeEach
    void setUp() {
        notificationRepo = mock(NotificationRepository.class);
        taskRepo = mock(TaskRepository.class);
        taskService = mock(ModelTaskService.class); // ← добавлено
        notificationService = new NotificationService(notificationRepo, taskRepo, taskService);
    }


    @Test
    void testGetUserNotifications_ReturnsCorrectList() {
        Long userId = 1L;
        Notification n1 = new Notification(1L, userId, 101L, "created");
        Notification n2 = new Notification(2L, userId, 102L, "deleted");

        when(notificationRepo.findByUserId(userId)).thenReturn(List.of(n1, n2));

        var result = notificationService.getUserNotifications(userId);

        assertEquals(2, result.size());
        assertTrue(result.contains(n1));
        assertTrue(result.contains(n2));
        verify(notificationRepo, times(1)).findByUserId(userId);
    }

    @Test
    void testGetAllNotifications_FiltersByValidTasks() {
        Notification n1 = new Notification(1L, 1L, 101L, "created");
        Notification n2 = new Notification(2L, 2L, 102L, "created");

        ZonedDateTime now = ZonedDateTime.now();

        Task validTask = new Task();
        validTask.setTaskId(101L);
        validTask.setUserId(1L);
        validTask.setTaskValue("Valid Task");
        validTask.setCreationDate(now.minusDays(1));
        validTask.setTargetDate(now.plusDays(1));

        Task invalidTask = new Task();
        invalidTask.setTaskId(102L);
        invalidTask.setUserId(2L);
        invalidTask.setTaskValue("Invalid Task");
        invalidTask.setCreationDate(now.plusDays(1));
        invalidTask.setTargetDate(now.minusDays(1));

        when(taskRepo.findAllAndDeletedFalse()).thenReturn(List.of(validTask, invalidTask));
        when(notificationRepo.findAll()).thenReturn(List.of(n1, n2));

        Collection<Notification> result = notificationService.getAllNotifications();

        assertEquals(1, result.size());
        assertTrue(result.contains(n1));
        assertFalse(result.contains(n2));

        verify(taskRepo, atLeastOnce()).findAllAndDeletedFalse();
        verify(notificationRepo, times(1)).findAll();
    }


}

