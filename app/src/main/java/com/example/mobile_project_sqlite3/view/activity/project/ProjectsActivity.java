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

public class ProjectsActivity extends AppCompatActivity  implements ProjectAdapter.OnProjectActionListener{
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
            startActivityForResult(intent, ADD_PROJECT_REQUEST);
        });

        loadProjects();
    }

    private void loadProjects() {
        List<Project> projects = projectController.getProjectsByUserId(userId);
        if (projects != null) {
            if (projectAdapter == null) {
                projectAdapter = new ProjectAdapter(projects,
                        this::onProjectClick,
                        this); // Pass this as the action listener
                rvProjects.setAdapter(projectAdapter);
            } else {
                projectAdapter.updateProjects(projects);
            }
        } else {
            Toast.makeText(this, "Failed to load projects", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == ADD_PROJECT_REQUEST || requestCode == EDIT_PROJECT_REQUEST)
                && resultCode == RESULT_OK) {
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
    public void onEditProject(Project project) {
        Intent intent = new Intent(this, EditProjectActivity.class);
        intent.putExtra("PROJECT", project);
        startActivityForResult(intent, EDIT_PROJECT_REQUEST);
    }
    private static final int EDIT_PROJECT_REQUEST = 2;

    @Override
    public void onDeleteProject(Project project) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Project")
                .setMessage("Are you sure you want to delete this project?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    boolean success = projectController.deleteProject(project.getId());
                    if (success) {
                        Toast.makeText(this, "Project deleted", Toast.LENGTH_SHORT).show();
                        loadProjects(); // Refresh the list
                    } else {
                        Toast.makeText(this, "Failed to delete project", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        projectController.close();
    }
}