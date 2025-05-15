package com.example.mobile_project_sqlite3.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.UserController;
import com.example.mobile_project_sqlite3.model.User;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvFullName, tvEmail, tvUsername;
    private UserController userController;
    private String username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userController = new UserController(this);
        username = getIntent().getStringExtra("USERNAME");

        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvUsername = findViewById(R.id.tvUsername);

        displayUserInfo();
    }

    private void displayUserInfo() {
        User user = userController.getUserByUsername(username);
        if (user != null) {
            tvFullName.setText(user.getFirstName() + " " + user.getLastName());
            tvEmail.setText(user.getEmail());
            tvUsername.setText(user.getUsername());
        }
    }
}