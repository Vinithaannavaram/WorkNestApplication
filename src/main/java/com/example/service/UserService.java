package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;

    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public User login(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }

}