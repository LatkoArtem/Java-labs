package com.projectmanagement.menuoptions;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.tasks.CompositeTask;

import java.util.Scanner;

public class CreateProjectOption {
    private final ProjectManager projectManager;

    public CreateProjectOption(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void createProject(Scanner scanner) {
        System.out.print("Введіть назву проекту, який хочете додати: ");
        String projectName = scanner.nextLine();

        // Перевіряємо, чи існує проєкт з такою назвою
        if (projectManager.getProjectByName(projectName) != null) {
            System.out.println("Проект з назвою '" + projectName + "' вже існує. Спробуйте іншу назву.");
            return;
        }

        // Якщо проєкту немає, додаємо його
        projectManager.addProject(new CompositeTask(projectName));
        System.out.println("Проект '" + projectName + "' успішно створено.");
    }
}
