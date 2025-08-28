package com.example.dao;

import com.example.model.Task;
import java.util.List;

public interface TaskDao {
    void saveTask(Task task);
    Task getTaskById(int id);
    List<Task> getAllTasks();
    List<Task> getDelayedTasks();
    void updateTask(Task task);
    void deleteTask(int id);
    List<Task> getTasksByUserId(int userId); 
}