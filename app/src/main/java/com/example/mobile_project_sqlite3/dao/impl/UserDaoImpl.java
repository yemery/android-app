package com.example.mobile_project_sqlite3.dao.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobile_project_sqlite3.dao.UserDao;
import com.example.mobile_project_sqlite3.db.DatabaseHelper;
import com.example.mobile_project_sqlite3.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserDaoImpl(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public void createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_FIRST_NAME, user.getFirstName());
        values.put(DatabaseHelper.KEY_LAST_NAME, user.getLastName());
        values.put(DatabaseHelper.KEY_EMAIL, user.getEmail());
        values.put(DatabaseHelper.KEY_USERNAME, user.getUsername());
        values.put(DatabaseHelper.KEY_PASSWORD, user.getPassword());

        database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    @Override
    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_FIRST_NAME, user.getFirstName());
        values.put(DatabaseHelper.KEY_LAST_NAME, user.getLastName());
        values.put(DatabaseHelper.KEY_EMAIL, user.getEmail());
        values.put(DatabaseHelper.KEY_USERNAME, user.getUsername());
        values.put(DatabaseHelper.KEY_PASSWORD, user.getPassword());

        database.update(DatabaseHelper.TABLE_USERS, values,
                DatabaseHelper.KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    @Override
    public void deleteUser(Long userId) {
        database.delete(DatabaseHelper.TABLE_USERS,
                DatabaseHelper.KEY_ID + " = ?",
                new String[]{String.valueOf(userId)});
    }

    @Override
    public User getUserById(Long userId) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.KEY_ID + " = ?",
                new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            User user = cursorToUser(cursor);
            cursor.close();
            return user;
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.KEY_USERNAME + " = ?",
                new String[]{username},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                User user = cursorToUser(cursor);
                cursor.close();
                return user;
            }
            cursor.close();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_USERS,
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                users.add(cursorToUser(cursor));
            }
            cursor.close();
        }
        return users;
    }

    @SuppressLint("Range")
    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        user.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FIRST_NAME)));
        user.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LAST_NAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PASSWORD)));
        return user;
    }
}