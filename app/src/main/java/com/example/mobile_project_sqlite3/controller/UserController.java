package com.example.mobile_project_sqlite3.controller;

import android.content.Context;

import com.example.mobile_project_sqlite3.db.DatabaseHelper;
import com.example.mobile_project_sqlite3.model.User;
import com.example.mobile_project_sqlite3.service.impl.UserServiceImpl;

import java.util.List;

public class UserController {
    private UserServiceImpl userService;

    public UserController(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        this.userService = new UserServiceImpl(dbHelper);
    }

    public void registerUser(User user) {
        userService.registerUser(user);
    }

    public boolean loginUser(String username, String password) {
        return userService.loginUser(username, password);
    }

    public void updateUser(User user) {
        userService.updateUserDetails(user);
    }

    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }

    public User getUser(long userId) {
        return userService.showUser(userId);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}