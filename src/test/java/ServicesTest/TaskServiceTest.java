package ServicesTest;

import com.project.demo.Exceptions.NotificationNotFoundException;
import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.NotificationRepository;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.db.repositories.UserRepository;
import com.project.demo.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepo;
    private NotificationRepository notificationRepo;
    private UserRepository userRepo;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepo = mock(TaskRepository.class);
        notificationRepo = mock(NotificationRepository.class);
        userRepo = mock(UserRepository.class);
        taskService = new TaskService(taskRepo, notificationRepo, userRepo);
    }

    @Test
    void insertTask_CreatesTaskAndNotification() {
        Long taskId = 1L;
        Long userId = 100L;
        String taskValue = "Test Task";
        ZonedDateTime targetDate = ZonedDateTime.now().plusDays(1);

        when(userRepo.existsByUserId(userId)).thenReturn(true);

        taskService.insertTask(taskId, userId, taskValue, targetDate);

        verify(taskRepo).insert(taskId, userId, taskValue, targetDate);
        verify(notificationRepo).saveNotification(argThat(n ->
                n.getUserId().equals(userId) &&
                        n.getTaskId().equals(taskId) &&
                        "created".equals(n.getNotificationValue())
        ));
    }

    @Test
    void insertTask_ThrowsException_WhenUserNotFound() {
        Long userId = 999L;
        when(userRepo.existsByUserId(userId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () ->
                taskService.insertTask(1L, userId, "value", ZonedDateTime.now())
        );
    }

    @Test
    void getTasks_ReturnsOnlyValidTasks() {
        ZonedDateTime now = ZonedDateTime.now();

        Task validTask = new Task();
        validTask.setTaskId(1L);
        validTask.setUserId(10L);
        validTask.setCreationDate(now.minusDays(1));
        validTask.setTargetDate(now.plusDays(1));

        Task invalidTask = new Task();
        invalidTask.setTaskId(2L);
        invalidTask.setUserId(20L);
        invalidTask.setCreationDate(now.plusDays(1));
        invalidTask.setTargetDate(now.minusDays(1));

        Notification deletedNotif = new Notification(2L, 20L, 2L, "deleted");

        when(taskRepo.findAllAndDeletedFalse()).thenReturn(List.of(validTask, invalidTask));
        when(notificationRepo.findByUserId(10L)).thenReturn(List.of());
        when(notificationRepo.findByUserId(20L)).thenReturn(List.of(deletedNotif));

        List<Task> result = (List<Task>) taskService.getTasks();

        assertEquals(1, result.size());
        assertTrue(result.contains(validTask));
        assertFalse(result.contains(invalidTask));
    }

    @Test
    void getAllTasks_ExcludesDeletedNotifications() {
        Task task1 = new Task();
        task1.setTaskId(1L);
        task1.setUserId(10L);

        Task task2 = new Task();
        task2.setTaskId(2L);
        task2.setUserId(20L);

        Notification deletedNotif = new Notification(2L, 20L, 2L, "deleted");

        when(taskRepo.findAllAndDeletedFalse()).thenReturn(List.of(task1, task2));
        when(notificationRepo.findByUserId(10L)).thenReturn(List.of());
        when(notificationRepo.findByUserId(20L)).thenReturn(List.of(deletedNotif));

        List<Task> result = (List<Task>) taskService.getAllTasks();

        assertEquals(1, result.size());
        assertTrue(result.contains(task1));
        assertFalse(result.contains(task2));
    }

    @Test
    void deleteTask_UpdatesNotificationToDeleted() {
        Notification notif = new Notification(1L, 100L, 1L, "created");

        when(notificationRepo.findByUserId(100L)).thenReturn(List.of(notif));

        taskService.deleteTask(100L, 1L);

        assertEquals("deleted", notif.getNotificationValue());
        verify(notificationRepo).saveNotification(notif);
    }

    @Test
    void deleteTask_ThrowsException_WhenNotificationNotFound() {
        when(notificationRepo.findByUserId(100L)).thenReturn(List.of());

        assertThrows(NotificationNotFoundException.class, () ->
                taskService.deleteTask(100L, 1L)
        );
    }
}

