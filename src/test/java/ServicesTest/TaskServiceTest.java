package ServicesTest;

import com.project.demo.db.Task;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.db.repositories.UserRepository;
import com.project.demo.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepo;
    private UserRepository userRepo;
    private KafkaTemplate<String, String> kafkaTemplate;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepo = mock(TaskRepository.class);
        userRepo = mock(UserRepository.class);
        kafkaTemplate = mock(KafkaTemplate.class);
        taskService = new TaskService(taskRepo, userRepo, kafkaTemplate);
    }

    @Test
    void insertTask_SendsKafkaEvent() {
        Long taskId = 1L;
        Long userId = 100L;
        String taskValue = "Test Task";
        ZonedDateTime targetDate = ZonedDateTime.now().plusDays(1);

        when(userRepo.existsByUserId(userId)).thenReturn(true);

        taskService.insertTask(taskId, userId, taskValue, targetDate);

        verify(taskRepo).insert(taskId, userId, taskValue, targetDate);
        verify(kafkaTemplate).send(eq("task-events"),
                eq(String.format("{\"taskId\":%d,\"userId\":%d,\"event\":\"created\"}", taskId, userId)));
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
    void deleteTask_SendsKafkaEvent() {
        Long taskId = 1L;
        Long userId = 100L;

        when(userRepo.existsByUserId(userId)).thenReturn(true);

        taskService.deleteTask(userId, taskId);

        verify(kafkaTemplate).send(eq("task-events"),
                eq(String.format("{\"taskId\":%d,\"userId\":%d,\"event\":\"deleted\"}", taskId, userId)));
    }

    @Test
    void deleteTask_ThrowsException_WhenUserNotFound() {
        Long userId = 999L;
        when(userRepo.existsByUserId(userId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () ->
                taskService.deleteTask(userId, 1L)
        );
    }

    @Test
    void getTasks_ReturnsValidTasks() {
        List<Task> tasks = List.of(new Task(), new Task());
        when(taskRepo.findAllAndDeletedFalse()).thenReturn(tasks);

        assertEquals(tasks, taskService.getTasks());
    }

    @Test
    void getAllTasks_ReturnsValidTasks() {
        List<Task> tasks = List.of(new Task(), new Task());
        when(taskRepo.findAllAndDeletedFalse()).thenReturn(tasks);

        assertEquals(tasks, taskService.getAllTasks());
    }
}
