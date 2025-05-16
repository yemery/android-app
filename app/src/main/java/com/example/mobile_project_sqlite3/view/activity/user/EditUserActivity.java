package com.example.mobile_project_sqlite3.view.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.UserController;
import com.example.mobile_project_sqlite3.model.User;

public class EditUserActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etEmail, etUsername, etPassword;
    private Button btnUpdate;
    private UserController userController;
    private long userId;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        userController = new UserController(this);
        userId = getIntent().getLongExtra("USER_ID", -1);
        currentUser = userController.getUser(userId);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnUpdate = findViewById(R.id.btnUpdate);

        if (currentUser != null) {
            etFirstName.setText(currentUser.getFirstName());
            etLastName.setText(currentUser.getLastName());
            etEmail.setText(currentUser.getEmail());
            etUsername.setText(currentUser.getUsername());
        }

        btnUpdate.setOnClickListener(v -> updateUser());
    }

    private void updateUser() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (firstName.isEmpty() || email.isEmpty() || username.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        currentUser.setUsername(username);
        if (!password.isEmpty()) {
            currentUser.setPassword(password);
        }

        userController.updateUser(currentUser);
        Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();

        // Return the updated username
        Intent resultIntent = new Intent();
        resultIntent.putExtra("UPDATED_USERNAME", username);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}