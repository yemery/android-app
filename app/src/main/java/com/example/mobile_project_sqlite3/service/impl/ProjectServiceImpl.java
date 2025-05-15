package com.example.mobile_project_sqlite3.service.impl;

import android.content.Context;

import com.example.mobile_project_sqlite3.dao.impl.ProjectDaoImpl;
import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.service.api.ProjectService;

import java.util.List;

public  class ProjectServiceImpl implements ProjectService {
    private ProjectDaoImpl projectDao;

    public ProjectServiceImpl(Context context) {
        this.projectDao = new ProjectDaoImpl(context);
        projectDao.open();
    }

    @Override
    public void createProject(Project project) {
        projectDao.createProject(project);
    }

    @Override
    public void updateProject(Long projectId, Project project) {
        project.setId(projectId);
        projectDao.updateProject(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectDao.deleteProject(projectId);
    }

    @Override
    public Project getProject(Long projectId) {
        return projectDao.getProject(projectId);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        return projectDao.getProjectsByUserId(userId);
    }

    @Override
    public void open() {

    }

    @Override
    public void updateProject(Project project) {

    }
}
