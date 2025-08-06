package InMemoryRepositoriesTest;

import com.project.demo.db.InMemory.TaskInMemoryRepository;
import com.project.demo.db.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TaskInMemoryRepositoryTest {

    private TaskInMemoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new TaskInMemoryRepository();
    }

    @Test
    void insert_AddsNewTaskWithCorrectFields() {
        Long taskId = 1L;
        Long userId = 100L;
        String taskValue = "Test Task";
        ZonedDateTime targetDate = ZonedDateTime.now().plusDays(1);

        repo.insert(taskId, userId, taskValue, targetDate);

        assertEquals(1, repo.tasks.size());
        Task task = repo.tasks.get(taskId);
        assertNotNull(task);
        assertEquals(taskId, task.getTaskId());
        assertEquals(userId, task.getUserId());
        assertEquals(taskValue, task.getTaskValue());
        assertEquals(targetDate, task.getTargetDate());
        assertNotNull(task.getCreationDate());
    }

    @Test
    void insert_OverwritesExistingTaskWithSameId() {
        Long taskId = 1L;
        ZonedDateTime targetDate1 = ZonedDateTime.now().plusDays(1);
        ZonedDateTime targetDate2 = ZonedDateTime.now().plusDays(2);

        repo.insert(taskId, 100L, "task1", targetDate1);
        repo.insert(taskId, 200L, "updated task1", targetDate2);

        assertEquals(1, repo.tasks.size());
        Task task = repo.tasks.get(taskId);
        assertEquals("updated task1", task.getTaskValue());
        assertEquals(200L, task.getUserId());
        assertEquals(targetDate2, task.getTargetDate());
    }

    @Test
    void findAllAndDeletedFalse_ReturnsAllTasks() {
        repo.insert(1L, 100L, "task1", ZonedDateTime.now().plusDays(1));
        repo.insert(2L, 200L, "task2", ZonedDateTime.now().plusDays(2));

        Collection<Task> result = repo.findAllAndDeletedFalse();

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(t -> t.getTaskId().equals(1L)));
        assertTrue(result.stream().anyMatch(t -> t.getTaskId().equals(2L)));
    }
}

