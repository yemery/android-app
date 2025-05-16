package com.example.mobile_project_sqlite3.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.UserController;
import com.example.mobile_project_sqlite3.model.User;
import com.example.mobile_project_sqlite3.view.activity.user.EditUserActivity;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvFirstName, tvLastName, tvEmail, tvUsername;
    private Button btnEdit, btnDelete;
    private UserController userController;
    private String username;
    private User currentUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userController = new UserController(this);
        username = getIntent().getStringExtra("USERNAME");

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvEmail = findViewById(R.id.tvEmail);
        tvUsername = findViewById(R.id.tvUsername);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        displayUserInfo();

        btnEdit.setOnClickListener(v -> editUser());
        btnDelete.setOnClickListener(v -> deleteUser());
    }

    private void displayUserInfo() {
        currentUser = userController.getUserByUsername(username);
        if (currentUser != null) {
            tvFirstName.setText(currentUser.getFirstName());
            tvLastName.setText(currentUser.getLastName());
            tvEmail.setText(currentUser.getEmail());
            tvUsername.setText(currentUser.getUsername());
        }
    }

    private void editUser() {
        if (currentUser != null) {
            Intent intent = new Intent(this, EditUserActivity.class);
            intent.putExtra("USER_ID", currentUser.getId());
            startActivityForResult(intent, 1);
        }
    }

    private void deleteUser() {
        if (currentUser != null) {
            new AlertDialog.Builder(this)
                    .setTitle("Delete User")
                    .setMessage("Are you sure you want to delete this user? This action cannot be undone.")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        userController.deleteUser(currentUser.getId());
                        Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();

                        // Navigate to LoginActivity and clear the back stack
                        Intent intent = new Intent(this, LoginActivity
                                .class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            displayUserInfo();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("UPDATED_USERNAME", currentUser.getUsername());
            setResult(RESULT_OK, resultIntent);
        }
    }
}