package com.project.demo.controllers;

import com.project.demo.services.ModelTaskService;
import com.project.demo.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/TaskController")
public class TaskController {
    private final ModelTaskService mapService;

    public TaskController(ModelTaskService mapService){
        this.mapService = mapService;
    }

    @GetMapping
    public Collection<Task> getAll(){
        return mapService.getAllTasks();
    }

    @GetMapping("/pendingTasks")
    public Collection<Task> get(){
        return mapService.getTask();
    }

    @PostMapping("/{idUser}")
    public void post(@RequestBody Task task, @PathVariable long idUser){
        mapService.putTask(task, idUser);
    }

    @DeleteMapping("/{idUser}/{idTask}")
    public void delete(@PathVariable long idUser, @PathVariable long idTask){
        mapService.deleteTask(idUser, idTask, "deleted");
    }
}
