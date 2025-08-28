package com.example.service;

import com.example.dao.TaskDao;
import com.example.model.Task;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	@Autowired
	private TaskDao taskDao;

    public void createTask(Task task) {
        taskDao.saveTask(task);
    }

    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    public void deleteTask(int id) {
        taskDao.deleteTask(id);
    }
    public void saveTask(Task task) {
    	 System.out.println("Saving task in service: " + task.getTitle());
        taskDao.saveTask(task);
    }
    public List<Task> getTasksByUserId(int userId) {
        return taskDao.getTasksByUserId(userId);
    }
    public void assignTaskToUser(int taskId, int userId) {
    	
    }
    public void updateStatus(int taskId, String status) {
        Task task = getTaskById(taskId);
        task.setStatus(status);
        updateTask(task);
    }
}