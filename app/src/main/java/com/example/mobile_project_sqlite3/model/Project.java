package com.example.mobile_project_sqlite3.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mobile_project_sqlite3.model.enums.Status;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Project  implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private Status status;

    private Long userId;

    private LocalDateTime createdAt;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Project(Long id, String name, String description, String startDate, String endDate, Status status , Long userId , LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.userId = userId;
        this.createdAt =LocalDateTime.now();

    }

    public Project() {

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

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status=" + status +
                '}';
    }
}