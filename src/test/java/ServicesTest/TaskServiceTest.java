package ServicesTest;

import com.project.demo.db.Task;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.db.repositories.UserRepository;
import com.project.demo.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
    void insertTask_SendsKafkaEvent_AndStoresUTCDate() {
        Long taskId = 1L;
        Long userId = 100L;
        String taskValue = "Test Task";
        ZonedDateTime localDate = ZonedDateTime.now().plusDays(1);

        when(userRepo.existsByUserId(userId)).thenReturn(true);

        taskService.insertTask(taskId, userId, taskValue, localDate);

        verify(taskRepo).insert(eq(taskId), eq(userId), eq(taskValue), argThat(date ->
                date.getOffset().equals(ZoneOffset.UTC)
        ));

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
    void insertTask_ThrowsException_WhenTargetDateInPast() {
        Long userId = 100L;
        when(userRepo.existsByUserId(userId)).thenReturn(true);

        ZonedDateTime pastDate = ZonedDateTime.now().minusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                taskService.insertTask(1L, userId, "Expired Task", pastDate)
        );
    }

    @Test
    void deleteTask_SendsKafkaEvent_AndDeletesTask() {
        Long taskId = 1L;
        Long userId = 100L;

        when(userRepo.existsByUserId(userId)).thenReturn(true);

        taskService.deleteTask(userId, taskId);

        verify(taskRepo).delete(eq(userId), eq(taskId));
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
