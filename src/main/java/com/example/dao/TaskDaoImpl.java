package com.example.dao;

import com.example.model.Task;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Override
    public void saveTask(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(task);
        tx.commit();
        session.close();
        System.out.println("Task saved to DB: " + task.getTitle());
    }

    @Override
    public Task getTaskById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task task = session.get(Task.class, id);
        session.close();
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("FROM Task", Task.class).list();
        session.close();
        return tasks;
    }

    @Override
    public List<Task> getDelayedTasks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("FROM Task WHERE dueDate < CURRENT_DATE AND status != 'Completed'", Task.class).list();
        session.close();
        return tasks;
    }

    @Override
    public void updateTask(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(task);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteTask(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Task task = session.get(Task.class, id);
        if (task != null) {
            session.delete(task);
        }
        tx.commit();
        session.close();
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery(
            "FROM Task WHERE assignedTo.id = :userId", Task.class)
            .setParameter("userId", userId)
            .list();
        session.close();
        return tasks;
    }
}