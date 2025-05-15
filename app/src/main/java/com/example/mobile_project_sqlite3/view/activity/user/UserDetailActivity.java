package com.example.mobile_project_sqlite3.view.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.UserController;
import com.example.mobile_project_sqlite3.model.User;

public class UserDetailActivity extends AppCompatActivity {
    private TextView tvUsername, tvEmail, tvFullName;
    private Button btnEdit, btnDelete;
    private UserController userController;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        userController = new UserController(this);
        userId = getIntent().getLongExtra("USER_ID", -1);

        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvFullName = findViewById(R.id.tvFullName);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        loadUserDetails();

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditUserActivity.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            userController.deleteUser(userId);
            Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void loadUserDetails() {
        User user = userController.getUser(userId);
        if (user != null) {
            tvUsername.setText(user.getUsername());
            tvEmail.setText(user.getEmail());
            tvFullName.setText(user.getFirstName() + " " + user.getLastName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserDetails();
    }
}