package com.project.demo.controllers;

import com.project.demo.db.Task;
import com.project.demo.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Collection<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/pending")
    public Collection<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping("/insert/{taskId}/{userId}/{taskValue}/{targetDate}")
    public void insertTask(@PathVariable Long taskId,
                           @PathVariable Long userId,
                           @PathVariable String taskValue,
                           @PathVariable ZonedDateTime targetDate) {
        taskService.insertTask(taskId, userId, taskValue, targetDate);
    }

    @DeleteMapping("/delete/{userId}/{taskId}")
    public void deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTask(userId, taskId);
    }
}