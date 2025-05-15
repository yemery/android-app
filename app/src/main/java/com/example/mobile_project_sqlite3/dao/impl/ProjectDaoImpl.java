package com.example.mobile_project_sqlite3.dao.impl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobile_project_sqlite3.dao.ProjectDao;
import com.example.mobile_project_sqlite3.db.DatabaseHelper;
import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private Context context;

    public ProjectDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public void open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        // Verify the projects table exists, create if not
        Cursor cursor = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='" +
                        DatabaseHelper.TABLE_PROJECTS + "'", null);

        if (cursor != null) {
            if (cursor.getCount() <= 0) {
                cursor.close();
                // Table doesn't exist, try to create it
                dbHelper.onCreate(database);
            } else {
                cursor.close();
            }
        }
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public void createProject(Project project) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_PROJECT_NAME, project.getName());
        values.put(DatabaseHelper.KEY_PROJECT_DESCRIPTION, project.getDescription());
        values.put(DatabaseHelper.KEY_START_DATE, project.getStartDate());
        values.put(DatabaseHelper.KEY_END_DATE, project.getEndDate());
        values.put(DatabaseHelper.KEY_STATUS, project.getStatus().name());
        values.put(DatabaseHelper.KEY_USER_ID, project.getUserId());

        database.insert(DatabaseHelper.TABLE_PROJECTS, null, values);
    }

    @Override
    public void updateProject(Project project) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_PROJECT_NAME, project.getName());
        values.put(DatabaseHelper.KEY_PROJECT_DESCRIPTION, project.getDescription());
        values.put(DatabaseHelper.KEY_START_DATE, project.getStartDate());
        values.put(DatabaseHelper.KEY_END_DATE, project.getEndDate());
        values.put(DatabaseHelper.KEY_STATUS, project.getStatus().name());
        values.put(DatabaseHelper.KEY_USER_ID, project.getUserId());

        database.update(DatabaseHelper.TABLE_PROJECTS, values,
                DatabaseHelper.KEY_PROJECT_ID + " = ?",
                new String[]{String.valueOf(project.getId())});
    }

    @Override
    public void deleteProject(Long projectId) {
        database.delete(DatabaseHelper.TABLE_PROJECTS,
                DatabaseHelper.KEY_PROJECT_ID + " = ?",
                new String[]{String.valueOf(projectId)});
    }

    @SuppressLint("Range")
    @Override
    public Project getProject(Long projectId) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_PROJECTS,
                null,
                DatabaseHelper.KEY_PROJECT_ID + " = ?",
                new String[]{String.valueOf(projectId)},
                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Project project = new Project();
        project.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_PROJECT_ID)));
        project.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PROJECT_NAME)));
        project.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PROJECT_DESCRIPTION)));
        project.setStartDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_START_DATE)));
        project.setEndDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_END_DATE)));
        project.setStatus(Status.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STATUS))));
        project.setUserId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.KEY_USER_ID)));

        cursor.close();
        return project;
    }

    @SuppressLint("Range")
    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PROJECTS,
                null, null, null, null, null, null);

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

    @SuppressLint("Range")
    @Override
    public List<Project> getProjectsByUserId(Long userId) {
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
