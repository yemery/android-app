package com.example.mobile_project_sqlite3.view.activity.project;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.model.Project;
import com.example.mobile_project_sqlite3.model.enums.Status;
import com.example.mobile_project_sqlite3.service.api.ProjectService;
import com.example.mobile_project_sqlite3.service.impl.ProjectServiceImpl;

import java.util.Calendar;

public class AddProjectActivity extends AppCompatActivity {
    private EditText etProjectName, etDescription, etStartDate, etEndDate;
    private Spinner spStatus;
    private Button btnAddProject;
    private ProjectService projectService;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        projectService = new ProjectServiceImpl(this);
        userId = getIntent().getLongExtra("USER_ID", -1);

        etProjectName = findViewById(R.id.etProjectName);
        etDescription = findViewById(R.id.etDescription);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        spStatus = findViewById(R.id.spStatus);
        btnAddProject = findViewById(R.id.btnAddProject);

        ArrayAdapter<Status> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Status.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(adapter);

        etStartDate.setOnClickListener(v -> showDatePickerDialog(etStartDate));
        etEndDate.setOnClickListener(v -> showDatePickerDialog(etEndDate));

        btnAddProject.setOnClickListener(v -> addProject());
    }

    private void showDatePickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    editText.setText(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void addProject() {
        String name = etProjectName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();
        Status status = (Status) spStatus.getSelectedItem();

        if (name.isEmpty()) {
            etProjectName.setError("Project name is required");
            return;
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setStatus(status);
        project.setUserId(userId);

        projectService.createProject(project);
        Toast.makeText(this, "Project added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}