package com.example.mobile_project_sqlite3.dao;

import com.example.mobile_project_sqlite3.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void close();

    void open();
}