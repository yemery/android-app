package com.example.mobile_project_sqlite3.controller;

import android.content.Context;

import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.service.api.ProjectService;
import com.example.mobile_project_sqlite3.service.impl.ProjectServiceImpl;

import java.util.List;

public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(Context context) {
        this.projectService = new ProjectServiceImpl(context);
    }

    public void open() {
        projectService.open();
    }

//    public void close() {
//        projectService.close();
//    }

    public long createProject(Project project) {
        try {
            projectService.createProject(project);
            return project.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean updateProject(Project project) {
        try {
            projectService.updateProject(project.getId(), project);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProject(long projectId) {
        try {
            projectService.deleteProject(projectId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Project getProjectById(long projectId) {
        try {
            return projectService.getProject(projectId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Project> getAllProjects() {
        try {
            return projectService.getAllProjects();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Project> getProjectsByUserId(long userId) {
        try {
            return projectService.getProjectsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}