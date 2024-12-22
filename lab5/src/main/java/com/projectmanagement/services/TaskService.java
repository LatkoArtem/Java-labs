package com.projectmanagement.services;

import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import com.projectmanagement.ProjectManager;

import java.util.Scanner;

public class TaskService {
    private final ProjectManager projectManager;

    public TaskService(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void addTaskToProject(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання, яке хочете додати: ");
            String taskName = scanner.nextLine();
            CompositeTask task = new CompositeTask(taskName);
            project.addSubTask(task);
            System.out.println("Завдання '" + taskName + "' додано до проекту '" + projectName + "'.");
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }

    public void addSubTaskToTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання: ");
            String taskName = scanner.nextLine();
            Task task = findTaskByName(project, taskName);

            if (task instanceof CompositeTask compositeTask) {
                System.out.print("Введіть назву підзавдання, яке хочете додати: ");
                String subTaskName = scanner.nextLine();
                compositeTask.addSubTask(new Task(subTaskName));
                System.out.println("Підзавдання '" + subTaskName + "' додано до завдання '" + taskName + "'.");
            } else {
                System.out.println("Завдання з назвою '" + taskName + "' не знайдено.");
            }
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }

    private Task findTaskByName(CompositeTask compositeTask, String name) {
        for (Task task : compositeTask.getSubTasks()) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task;
            }
            if (task instanceof CompositeTask) {
                Task found = findTaskByName((CompositeTask) task, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
