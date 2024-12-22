package com.projectmanagement.menuoptions;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.tasks.CompositeTask;

import java.util.Scanner;

public class AddTaskToProjectOption {
    private final ProjectManager projectManager;

    public AddTaskToProjectOption(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void addTaskToProject(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);
        if (project != null) {
            if (project.isCompleted()) {
                System.out.println("Проект '" + projectName + "' вже виконано. Не можна додавати нові завдання або підзавдання.");
            } else {
                System.out.print("Введіть назву завдання, яке хочете додати: ");
                String taskName = scanner.nextLine();

                // Створюємо завдання як CompositeTask з самого початку
                CompositeTask task = new CompositeTask(taskName);
                if (project.addSubTask(task)) {

                    System.out.println("Завдання '" + taskName + "' додано до проекту '" + projectName + "'.");
                } else {
                    System.out.println("Завдання з назвою '" + taskName + "' вже існує.");
                }
            }
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }
}
