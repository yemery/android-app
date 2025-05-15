package com.example.mobile_project_sqlite3.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.UserController;
import com.example.mobile_project_sqlite3.model.User;
import com.example.mobile_project_sqlite3.view.activity.project.ProjectsActivity;
import com.example.mobile_project_sqlite3.view.activity.user.UsersListActivity;

public class DashboardActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private Button btnLogout, btnViewProfile, btnViewUsers, btnViewProjects;
    private UserController userController;
    private String username;
    private long userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userController = new UserController(this);
        username = getIntent().getStringExtra("USERNAME");
        userId = getIntent().getLongExtra("USER_ID", -1);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        btnViewProfile = findViewById(R.id.btnViewProfile);
        btnViewUsers = findViewById(R.id.btnViewUsers);
        btnViewProjects = findViewById(R.id.btnViewProjects);

        // Display welcome message
        User user = userController.getUserByUsername(username);
        if (user != null) {
            tvWelcome.setText("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
        }

        btnViewProfile.setOnClickListener(v -> viewProfile());
        btnViewUsers.setOnClickListener(v -> viewUsersList());
        btnViewProjects.setOnClickListener(v -> viewProjects());
        btnLogout.setOnClickListener(v -> logout());
    }

    private void viewProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("USERNAME", username);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    private void viewUsersList() {
        Intent intent = new Intent(this, UsersListActivity.class);
        startActivity(intent);
    }

    private void viewProjects() {
        Intent intent = new Intent(this, ProjectsActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    private void logout() {
        finish();
    }
}