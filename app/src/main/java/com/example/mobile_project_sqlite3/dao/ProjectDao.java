package com.example.mobile_project_sqlite3.dao;

import com.example.mobile_project_sqlite3.model.Project;

import java.util.List;

public interface ProjectDao {

    void open();

    void close();

    void createProject(Project project);

    void updateProject(Project project);

    void deleteProject(Long projectId);

    Project getProject(Long projectId);

    List<Project> getAllProjects();

    List<Project> getProjectsByUserId(Long userId);
}
