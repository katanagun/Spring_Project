package com.project.demo.controllers;

import com.project.demo.services.ModelTaskService;
import com.project.demo.models.Task;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/TaskController")
public class TaskController {
    private final ModelTaskService modelTaskService;

    public TaskController(ModelTaskService modelTaskService){
        this.modelTaskService = modelTaskService;
    }

    @GetMapping
    public Collection<Task> getAll(){
        return modelTaskService.getAllTasks();
    }

    @GetMapping("/pendingTasks")
    public Collection<Task> get(){
        return modelTaskService.getTasks();
    }

    @PostMapping("/{idTask}/{idUser}/{valueTask}/{targetDate}")
    public void post(@PathVariable long idTask, @PathVariable long idUser, @PathVariable String valueTask, @PathVariable ZonedDateTime targetDate){
        modelTaskService.insertTask(idTask, idUser, valueTask, targetDate);
    }

    @DeleteMapping("/{idUser}/{idTask}")
    public void delete(@PathVariable long idUser, @PathVariable long idTask){
        modelTaskService.deleteTask(idUser, idTask, "deleted");
    }
}