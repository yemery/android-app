package com.example.mobile_project_sqlite3.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.model.User;
import com.example.mobile_project_sqlite3.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public DatabaseOperations(Context context) {
        this.context = context;
    }

    public DatabaseOperations open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Add a new user
    public long addUser(String firstName, String lastName,
                        String email, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_FIRST_NAME, firstName);
        values.put(DatabaseHelper.KEY_LAST_NAME, lastName);
        values.put(DatabaseHelper.KEY_EMAIL, email);
        values.put(DatabaseHelper.KEY_USERNAME, username);
        values.put(DatabaseHelper.KEY_PASSWORD, password);

        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    // Get all users
    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_USERS
               ;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Long.valueOf(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID))));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FIRST_NAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LAST_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_USERNAME)));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userList;
    }
    // In DatabaseOperations.java
    public long addProject(Project project) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_PROJECT_NAME, project.getName());
        values.put(DatabaseHelper.KEY_PROJECT_DESCRIPTION, project.getDescription());
        values.put(DatabaseHelper.KEY_START_DATE, project.getStartDate());
        values.put(DatabaseHelper.KEY_END_DATE, project.getEndDate());
        values.put(DatabaseHelper.KEY_STATUS, project.getStatus().name());
        values.put(DatabaseHelper.KEY_USER_ID, project.getUserId());

        return database.insert(DatabaseHelper.TABLE_PROJECTS, null, values);
    }

    @SuppressLint("Range")
    public List<Project> getProjectsByUserId(long userId) {
        List<Project> projects = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_PROJECTS,
                null,
                DatabaseHelper.KEY_USER_ID + " = ?",
                new String[]{String.valueOf(userId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_PROJECT_ID)));
                project.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PROJECT_NAME)));
                project.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PROJECT_DESCRIPTION)));
                project.setStartDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_START_DATE)));
                project.setEndDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_END_DATE)));
                project.setStatus(Status.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STATUS))));
                project.setUserId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_USER_ID)));

                projects.add(project);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return projects;
    }

}