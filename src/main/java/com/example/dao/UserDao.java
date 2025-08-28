package com.example.dao;

import com.example.model.User;
import java.util.List;

public interface UserDao {
    void saveUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmailAndPassword(String email,String  password);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}