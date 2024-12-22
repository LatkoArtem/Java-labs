package com.projectmanagement.services;

import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.ProjectManager;

import java.util.Scanner;

public class ProjectService {
    private final ProjectManager projectManager;

    public ProjectService(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void createProject(Scanner scanner) {
        System.out.print("Введіть назву проекту, який хочете додати: ");
        String projectName = scanner.nextLine();

        if (projectManager.getProjectByName(projectName) != null) {
            System.out.println("Проект з назвою '" + projectName + "' вже існує. Спробуйте іншу назву.");
            return;
        }

        projectManager.addProject(new CompositeTask(projectName));
        System.out.println("Проект '" + projectName + "' успішно створено.");
    }

    public void viewProjects() {
        System.out.println("\n--- Список проектів ---");
        for (CompositeTask project : projectManager.getAllProjects()) {
            System.out.println("- Проект: " + project.getName());
        }
    }

    public void deleteProject(String projectName) {
        if (projectManager.removeProjectOrTask(projectName)) {
            System.out.println("Проект '" + projectName + "' видалено.");
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }
}
