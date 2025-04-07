package com.project.demo.controllers;

import com.project.demo.services.ModelTaskService;
import com.project.demo.db.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TaskController")
public class TaskController {
    private final ModelTaskService taskService;

    public TaskController(ModelTaskService mapService){
        this.taskService = mapService;
    }

    @GetMapping("/{key}")
    public Task get(@PathVariable long idNotification){
        return taskService.getTask(idNotification);
    }

    @PostMapping()
    public void post(@RequestBody Task task){
        taskService.putTask(task);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable long key){
        taskService.deleteTask(key);
    }
}
