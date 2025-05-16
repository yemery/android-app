package com.example.mobile_project_sqlite3.view.activity.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
    private static final int ADD_PROJECT_REQUEST = 1; // Add this constant

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
            startActivityForResult(intent, ADD_PROJECT_REQUEST); // Changed to startActivityForResult
        });

        loadProjects();
    }

    private void loadProjects() {
        List<Project> projects = projectController.getProjectsByUserId(userId);
        if (projects != null) {
            if (projectAdapter == null) {
                projectAdapter = new ProjectAdapter(projects, this::onProjectClick);
                rvProjects.setAdapter(projectAdapter);
            } else {
                projectAdapter.updateProjects(projects); // Update existing adapter
            }
        } else {
            Toast.makeText(this, "Failed to load projects", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PROJECT_REQUEST && resultCode == RESULT_OK) {
            loadProjects(); // Refresh the project list
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