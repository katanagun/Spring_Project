package com.project.demo.services;

import com.project.demo.db.Task;
import com.project.demo.db.repository.TaskRep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRep taskRep;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldPutTask() {
        Task task = new Task();
        task.setId(1);
        task.setValue("test task 1");

        taskService.putTask(task);

        verify(taskRep).save(task);
    }

    @Test
    void shouldGetTask() {
        long taskId = 2;
        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setValue("test value 2");

        when(taskRep.findById(taskId)).thenReturn(Optional.of(mockTask));

        Task retrievedTask = taskService.getTask(taskId);

        assertNotNull(retrievedTask);
        assertEquals("test value 2", retrievedTask.getValue());
        verify(taskRep).findById(taskId);
    }

    @Test
    void shouldReturnNullTaskMissing() {
        long taskId = 1;

        when(taskRep.findById(taskId)).thenReturn(Optional.empty());

        assertNull(taskService.getTask(taskId));
        verify(taskRep).findById(taskId);
    }

    @Test
    void shouldDeleteTask() {
        long taskId = 3;

        taskService.deleteTask(taskId);

        verify(taskRep).deleteById(taskId);
    }
}