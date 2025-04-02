package controllers;

import models.ModelTaskService;
import models.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TaskController")
public class TaskController {
    private final ModelTaskService mapService;

    public TaskController(ModelTaskService mapService){
        this.mapService = mapService;
    }

    @GetMapping("/{key}")
    public Task get(@PathVariable int key){
        return mapService.getData(key);
    }

    @PostMapping("/{key}")
    public String post(@PathVariable int key, @RequestBody Task task){
        mapService.putData(key, task);
        return "post success";
    }

    @DeleteMapping("/{key}")
    public String delete(@PathVariable int key){
        mapService.deleteData(key);
        return "delete success";
    }
}
