package com.example.mobile_project_sqlite3.service.impl;

import com.example.mobile_project_sqlite3.db.DatabaseHelper;
import com.example.mobile_project_sqlite3.dao.UserDao;
import com.example.mobile_project_sqlite3.dao.impl.UserDaoImpl;
import com.example.mobile_project_sqlite3.model.User;
import com.example.mobile_project_sqlite3.service.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(DatabaseHelper dbHelper) {
        this.userDao = new UserDaoImpl(dbHelper);
        this.userDao.open();
    }

    @Override
    public void registerUser(User user) {
        // Check if username already exists
        if (userDao.getUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        userDao.createUser(user);
    }

    @Override
    public boolean loginUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void updateUserDetails(User user) {
        userDao.updateUser(user);
    }





    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser( (Long)  userId);
    }

    @Override
    public User showUser(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}