package com.projectmanagement.menuoptions;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import com.projectmanagement.utils.TaskFinder;

import java.util.Scanner;

public class DeleteTaskOrProjectOption {
    private final ProjectManager projectManager;

    public DeleteTaskOrProjectOption(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void deleteTaskOrProject(Scanner scanner) {
        System.out.println("\nЩо ви хочете видалити?");
        System.out.println("1. Проект");
        System.out.println("2. Завдання");
        System.out.println("3. Підзавдання");
        System.out.print("Оберіть опцію: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> deleteProject(scanner);
            case 2 -> deleteTask(scanner);
            case 3 -> deleteSubTask(scanner);
            default -> System.out.println("Невірна опція. Спробуйте ще раз.");
        }
    }

    private void deleteProject(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        if (projectManager.removeProjectOrTask(projectName)) {
            System.out.println("Проект '" + projectName + "' видалено.");
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання: ");
            String taskName = scanner.nextLine();
            if (deleteFromComposite(project, taskName)) {
                System.out.println("Завдання '" + taskName + "' видалено.");
            } else {
                System.out.println("Завдання не знайдено.");
            }
        } else {
            System.out.println("Проект не знайдено.");
        }
    }

    private void deleteSubTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання: ");
            String taskName = scanner.nextLine();
            Task task = TaskFinder.findTaskByName(project, taskName);

            if (task instanceof CompositeTask compositeTask) {
                System.out.print("Введіть назву підзавдання: ");
                String subTaskName = scanner.nextLine();
                if (deleteFromComposite(compositeTask, subTaskName)) {
                    System.out.println("Підзавдання видалено.");
                } else {
                    System.out.println("Підзавдання не знайдено.");
                }
            } else {
                System.out.println("Завдання не є складовим.");
            }
        } else {
            System.out.println("Проект не знайдено.");
        }
    }

    private boolean deleteFromComposite(CompositeTask composite, String name) {
        return composite.getSubTasks().removeIf(task -> task.getName().equalsIgnoreCase(name));
    }
}
