package com.project.demo.controllers;

import com.project.demo.services.ModelTaskService;
import com.project.demo.models.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TaskController")
public class TaskController {
    private final ModelTaskService mapService;

    public TaskController(ModelTaskService mapService){
        this.mapService = mapService;
    }

    @GetMapping("/{key}")
    public Task get(@PathVariable long key){
        return mapService.getData(key);
    }

    @PostMapping()
    public void post(@RequestBody Task task){
        mapService.putData(task);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable long key){
        mapService.deleteData(key);
    }
}
