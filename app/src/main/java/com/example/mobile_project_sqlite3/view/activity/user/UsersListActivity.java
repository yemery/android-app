package com.example.mobile_project_sqlite3.view.activity.user;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.UserController;
import com.example.mobile_project_sqlite3.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {
    private ListView lvUsers;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userController = new UserController(this);
        lvUsers = findViewById(R.id.lvUsers);

        displayUsersList();
    }

    private void displayUsersList() {
        List<User> users = userController.getAllUsers();
        List<String> userStrings = new ArrayList<>();

        for (User user : users) {
            userStrings.add(user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                userStrings
        );

        lvUsers.setAdapter(adapter);
    }
}