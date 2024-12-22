package com.projectmanagement.menuoptions;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import com.projectmanagement.utils.TaskFinder; // Імпортуємо утилітний клас

import java.util.Scanner;

public class ExecuteTaskOption {
    private final ProjectManager projectManager;

    public ExecuteTaskOption(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void executeTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання або підзавдання (або залиште порожнім для виконання всього проекту): ");
            String taskName = scanner.nextLine();

            if (taskName.isEmpty()) {
                project.execute();
                System.out.println("Всі завдання у проекті '" + projectName + "' виконано.");
            } else {
                Task task = TaskFinder.findTaskByName(project, taskName); // Використовуємо TaskFinder

                if (task != null) {
                    task.execute();
                } else {
                    System.out.println("Завдання з назвою '" + taskName + "' не знайдено.");
                }
            }
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }
}
