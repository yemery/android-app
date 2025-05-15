package com.example.mobile_project_sqlite3.service.api;

import com.example.mobile_project_sqlite3.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    boolean loginUser(String username, String password);
    void updateUserDetails(User user);
    void deleteUser(Long userId);
    User showUser(Long userId);
    public List<User> getAllUsers();

    User getUserByUsername(String username);
}