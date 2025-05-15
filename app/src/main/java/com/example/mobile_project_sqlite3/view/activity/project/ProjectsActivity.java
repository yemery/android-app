package com.example.mobile_project_sqlite3.view.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.ProjectController;
import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.service.api.ProjectService;
import com.example.mobile_project_sqlite3.service.impl.ProjectServiceImpl;
import com.example.mobile_project_sqlite3.view.adapter.ProjectAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProjectsActivity extends AppCompatActivity {
    private RecyclerView rvProjects;
    private ProjectAdapter projectAdapter;
    private ProjectController projectController;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        projectController = new ProjectController(this);
        projectController.open();

        userId = getIntent().getLongExtra("USER_ID", -1);

        rvProjects = findViewById(R.id.rvProjects);
        rvProjects.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabAddProject = findViewById(R.id.fabAddProject);
        fabAddProject.setOnClickListener(v -> {
            Intent intent = new Intent(ProjectsActivity.this, AddProjectActivity.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        });

        loadProjects();
    }

    private void loadProjects() {
        List<Project> projects = projectController.getProjectsByUserId(userId);
        if (projects != null) {
            projectAdapter = new ProjectAdapter(projects, this::onProjectClick);
            rvProjects.setAdapter(projectAdapter);
        } else {
            Toast.makeText(this, "Failed to load projects", Toast.LENGTH_SHORT).show();
        }
    }

    private void onProjectClick(Project project) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Project Details");
        builder.setMessage("Name: " + project.getName() + "\nDescription: " + project.getDescription());
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // ... rest of your activity methods ...

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        projectController.close();
    }
}