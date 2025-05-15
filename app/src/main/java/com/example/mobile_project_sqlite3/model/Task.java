package com.example.mobile_project_sqlite3.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mobile_project_sqlite3.model.enums.Status;

import java.time.LocalDateTime;

public class Task {

    private Long id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private boolean isCompleted;

    private Status status;
    private Long projectId;

    private LocalDateTime createdAt;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task(Long id, String name, String description, String startDate, String endDate, boolean isCompleted, Status status , Long projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCompleted = isCompleted;
        this.status = status;
        this.projectId = projectId;
        this.createdAt = LocalDateTime.now();

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isCompleted=" + isCompleted +
                ", status=" + status +
                ", projectId=" + projectId +
                '}';
    }
}