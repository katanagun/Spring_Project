package models;

public interface ModelTaskService {
    public void putData(int key, Task task);
    public Task getData(int key);
    public void deleteData(int key);
}
