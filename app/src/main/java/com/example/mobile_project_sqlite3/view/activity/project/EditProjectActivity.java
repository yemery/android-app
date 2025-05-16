package com.example.mobile_project_sqlite3.view.activity.project;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.controller.ProjectController;
import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.model.enums.Status;

public class EditProjectActivity extends AppCompatActivity {

    private EditText etProjectName, etProjectDescription, etStartDate, etEndDate;
    private ProjectController projectController;
    private Project project;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        projectController = new ProjectController(this);
        projectController.open();

        etProjectName = findViewById(R.id.etProjectName);
        etProjectDescription = findViewById(R.id.etProjectDescription);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        Button btnSave = findViewById(R.id.btnSave);

        // Get project from intent
        project = (Project) getIntent().getSerializableExtra("PROJECT");

        if (project != null) {
            etProjectName.setText(project.getName());
            etProjectDescription.setText(project.getDescription());
            etStartDate.setText(project.getStartDate());
            etEndDate.setText(project.getEndDate());
        }

        btnSave.setOnClickListener(v -> saveProject());
    }

    private void saveProject() {
        String name = etProjectName.getText().toString().trim();
        String description = etProjectDescription.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        // You might want to add status selection here

        boolean success = projectController.updateProject(project);
        if (success) {
            Toast.makeText(this, "Project updated successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Failed to update project", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        projectController.close();
    }
}
