package com.projectmanagement.menuoptions;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import com.projectmanagement.utils.TaskFinder;

import java.util.Scanner;

public class AddSubTaskToTaskOption {
    private final ProjectManager projectManager;

    public AddSubTaskToTaskOption(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void addSubTaskToTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            if (project.isCompleted()) {
                System.out.println("Проект '" + projectName + "' вже виконано. Не можна додавати нові завдання або підзавдання.");
            } else {
                System.out.print("Введіть назву завдання: ");
                String taskName = scanner.nextLine();
                Task task = TaskFinder.findTaskByName(project, taskName);

                if (task instanceof CompositeTask compositeTask) {
                    // Завдання має бути CompositeTask для додавання підзавдань

                    System.out.print("Введіть назву підзавдання, яке хочете додати: ");
                    String subTaskName = scanner.nextLine();

                    if (compositeTask.addSubTask(new Task(subTaskName))) {
                        System.out.println("Підзавдання '" + subTaskName + "' додано до завдання '" + taskName + "'.");
                    } else {
                        System.out.println("Підзавдання з назвою '" + subTaskName + "' вже існує.");
                    }
                } else {
                    System.out.println("Завдання з назвою '" + taskName + "' не є CompositeTask.");
                }
            }
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }
}
