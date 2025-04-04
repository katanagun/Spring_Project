package com.project.demo.services;

import com.project.demo.models.Task;

public interface ModelTaskService {
    public void putData(Task task);
    public Task getData(long key);
    public void deleteData(long key);
}
