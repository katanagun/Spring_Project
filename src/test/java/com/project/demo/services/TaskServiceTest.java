package com.project.demo.services;

import com.project.demo.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    public void setup(){
        taskService = new TaskService();
    }

    @Test
    public void shouldPutTaskMap(){
        Task task = new Task();
        task.setIdTask(1);
        task.setValueTask("test task 1");
        taskService.putTask(task);

        assertNotNull(taskService.getTask(1));
        assertEquals("test task 1", taskService.getTask(1).getValueTask());
    }

    @Test
    public void shouldGetTaskMap(){
        Task task = new Task();
        task.setIdTask(2);
        task.setValueTask("test value 2");
        taskService.putTask(task);

        Task gTask = taskService.getTask(2);

        assertNotNull(gTask);
        assertEquals("test value 2", gTask.getValueTask());
    }

    @Test
    public void shouldReturnNullTaskMissing(){
        assertNull(taskService.getTask(1));
    }

    @Test
    public void shouldDeleteTaskMap(){
        Task task = new Task();
        task.setIdTask(3);
        task.setValueTask("test value 3");
        taskService.putTask(task);

        taskService.deleteTask(3);

        assertNull(taskService.getTask(3));
    }
}
