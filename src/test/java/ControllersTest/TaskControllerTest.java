package ControllersTest;

import com.project.demo.controllers.TaskController;
import com.project.demo.db.Task;
import com.project.demo.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService);
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task(1L, 101L, "Task A", ZonedDateTime.now(), ZonedDateTime.now().plusDays(1));
        Task task2 = new Task(2L, 102L, "Task B", ZonedDateTime.now(), ZonedDateTime.now().plusDays(2));
        List<Task> mockTasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(mockTasks);

        var result = taskController.getAllTasks();

        assertEquals(2, result.size());
        assertTrue(result.contains(task1));
        assertTrue(result.contains(task2));
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetPendingTasks() {
        Task task1 = new Task(3L, 103L, "test task", ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));
        List<Task> mockTasks = List.of(task1);

        when(taskService.getTasks()).thenReturn(mockTasks);

        var result = taskController.getTasks();

        assertEquals(1, result.size());
        assertEquals("test task", result.iterator().next().getTaskValue());
        verify(taskService, times(1)).getTasks();
    }

    @Test
    void testInsertTask() {
        Long taskId = 10L;
        Long userId = 200L;
        String taskValue = "task1";
        ZonedDateTime targetDate = ZonedDateTime.now().plusDays(5);

        taskController.insertTask(taskId, userId, taskValue, targetDate);

        verify(taskService, times(1)).insertTask(taskId, userId, taskValue, targetDate);
    }

    @Test
    void testDeleteTask() {
        Long userId = 300L;
        Long taskId = 20L;

        taskController.deleteTask(userId, taskId);

        verify(taskService, times(1)).deleteTask(userId, taskId);
    }
}
