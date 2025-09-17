package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import com.project.demo.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;
    private UserService userService;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        notificationService = new NotificationService(taskService);
        taskService = new TaskService(userService, notificationService);

        userService.insertUser(1L, "Test User");
    }

    @Test
    void testInsertTask() {
        long idTask = 101L;
        long idUser = 1L;
        String taskValue = "Test Task";
        ZonedDateTime targetDate = ZonedDateTime.now().plusDays(5);

        taskService.insertTask(idTask, idUser, taskValue, targetDate);

        Task task = taskService.tasks.get(idTask);

        assertNotNull(task);
        assertEquals(idTask, task.getIdTask());
        assertEquals(idUser, task.getIdUser());
        assertEquals(taskValue, task.getValueTask());
    }

    @Test
    void testInsertTaskForNonExistingUser() {
        long idTask = 102L;
        long idUser = 999L;
        String taskValue = "Should Fail";
        ZonedDateTime targetDate = ZonedDateTime.now().plusDays(5);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                taskService.insertTask(idTask, idUser, taskValue, targetDate));

        assertEquals("User with id " + idUser + " not found.", exception.getMessage());
    }

    @Test
    void testGetAllTasks() {
        taskService.insertTask(101L, 1L, "Task 1", ZonedDateTime.now().plusDays(5));
        taskService.insertTask(102L, 1L, "Task 2", ZonedDateTime.now().plusDays(5));

        Collection<Task> allTasks = taskService.getAllTasks();

        assertEquals(2, allTasks.size());
    }

    @Test
    void testGetTasksWhenNotificationDeleted() {
        taskService.insertTask(101L, 1L, "Task 1", ZonedDateTime.now().plusDays(5));
        taskService.insertTask(102L, 1L, "Task 2", ZonedDateTime.now().plusDays(5));

        taskService.deleteTask(1L, 102L, "deleted");

        Collection<Task> tasks = taskService.getTasks();

        assertEquals(1, tasks.size());
        assertTrue(tasks.stream().anyMatch(task -> task.getIdTask() == 101L));
        assertFalse(tasks.stream().anyMatch(task -> task.getIdTask() == 102L));
    }

    @Test
    void testDeleteTask() {
        taskService.insertTask(101L, 1L, "Task 1", ZonedDateTime.now().plusDays(5));

        taskService.deleteTask(1L, 101L, "deleted");

        Notification notification = notificationService.notifications.get(101L);

        assertNotNull(notification);
        assertEquals("deleted", notification.getValue());
    }
}
