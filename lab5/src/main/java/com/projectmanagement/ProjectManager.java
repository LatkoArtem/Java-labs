package com.projectmanagement;

import com.projectmanagement.tasks.CompositeTask;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    private final List<CompositeTask> projects = new ArrayList<>();

    public void addProject(CompositeTask project) {
        projects.add(project);
    }

    public CompositeTask getProjectByName(String name) {
        for (CompositeTask project : projects) {
            if (project.getName().equalsIgnoreCase(name)) {
                return project;
            }
        }
        return null; // Проект не знайдено
    }

    public List<CompositeTask> getAllProjects() {
        return projects;
    }

    public boolean removeProjectOrTask(String name) {
        for (CompositeTask project : projects) {
            if (project.getName().equalsIgnoreCase(name)) {
                projects.remove(project);
                return true;
            }
            if (project.removeSubTaskByName(name)) {
                return true;
            }
        }
        return false;
    }
}
