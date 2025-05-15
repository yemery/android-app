package com.example.mobile_project_sqlite3.service.api;

import com.example.mobile_project_sqlite3.model.Project;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    void updateProject(Long projectId, Project project);

    void deleteProject(Long projectId);

    Project getProject(Long projectId);

    List<Project> getAllProjects();

    List<Project> getProjectsByUserId(Long userId);

    void open();

    void updateProject(Project project);
}
