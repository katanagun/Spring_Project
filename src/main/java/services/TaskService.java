package services;

import models.ModelTaskService;
import models.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService implements ModelTaskService {
    Map<Integer, Task> data = new HashMap<>();

    public void putData(int key, Task task){
        data.put(key, task);
    }

    public Task getData(int key){
        return data.get(key);
    }

    public void deleteData(int key){
        data.remove(key);
    }
}
