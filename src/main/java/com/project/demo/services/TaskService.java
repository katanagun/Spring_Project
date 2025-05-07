package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService implements ModelTaskService {
    private final UserService userService;
    private final NotificationService notificationService;

    public TaskService(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    Map<Long, Task> tasks = new HashMap<>();

    public void putTask(Task task, long idUser){
        tasks.put(task.getIdTask(), task);
        userService.users.get(idUser).setIdTask(task.getIdTask());
    }

    public Collection<Task> getAllTasks(){
        Collection<Task> filteredTasks = new ArrayList<>();
        for (Task task: tasks.values()){
            if(notificationService.notifications.get(task.getIdTask()).getValue() != "deleted"){
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public Collection<Task> getTask(){
        Collection<Task> pendingTasks = new ArrayList<>();
        for (Task task: tasks.values()){
            if (task.getCreationDate().isBefore(task.getTargetDate()) && notificationService.notifications.get(task.getIdTask()).getValue() != "deleted"){
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

    public void deleteTask(long idUser, long idTask, String notification){
        notificationService.notifications.put(idTask, new Notification(idUser, idTask, "deleted"));
    }

}
