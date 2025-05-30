package com.example.mobile_project_sqlite3.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_project_sqlite3.R;
import com.example.mobile_project_sqlite3.model.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private final List<Project> projects;
    private final OnProjectClickListener onProjectClickListener;
    private final OnProjectActionListener onProjectActionListener;

    public interface OnProjectClickListener {
        void onProjectClick(Project project);
    }

    public interface OnProjectActionListener {
        void onEditProject(Project project);
        void onDeleteProject(Project project);
    }

    public ProjectAdapter(List<Project> projects,
                          OnProjectClickListener onProjectClickListener,
                          OnProjectActionListener onProjectActionListener) {
        this.projects = projects;
        this.onProjectClickListener = onProjectClickListener;
        this.onProjectActionListener = onProjectActionListener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.bind(project);
        holder.itemView.setOnClickListener(v -> onProjectClickListener.onProjectClick(project));

        holder.btnEdit.setOnClickListener(v -> onProjectActionListener.onEditProject(project));
        holder.btnDelete.setOnClickListener(v -> onProjectActionListener.onDeleteProject(project));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void updateProjects(List<Project> newProjects) {
        this.projects.clear();
        this.projects.addAll(newProjects);
        notifyDataSetChanged();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProjectName;
        private final TextView tvProjectStatus;
        private final TextView tvProjectDates;
        private final Button btnEdit;
        private final Button btnDelete;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProjectName = itemView.findViewById(R.id.tvProjectName);
            tvProjectStatus = itemView.findViewById(R.id.tvProjectStatus);
            tvProjectDates = itemView.findViewById(R.id.tvProjectDates);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(Project project) {
            tvProjectName.setText(project.getName());
            tvProjectStatus.setText(project.getStatus().toString());
            tvProjectDates.setText(String.format("%s - %s",
                    project.getStartDate(),
                    project.getEndDate()));
        }
    }
}