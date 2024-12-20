package com.projectmanagement;

import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;

import java.util.Scanner;

public class MainMenu {
    private static final ProjectManager projectManager = new ProjectManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Оберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createProject(scanner);
                    break;
                case 2:
                    addTaskToProject(scanner);
                    break;
                case 3:
                    addSubTaskToTask(scanner);
                    break;
                case 4:
                    executeTask(scanner);
                    break;
                case 5:
                    viewProjects();
                    break;
                case 6:
                    deleteTaskOrProject(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Дякуємо за використання системи управління проектами!");
                    break;
                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1. Додати новий проект");
        System.out.println("2. Додати завдання до проекту");
        System.out.println("3. Додати підзавдання до завдання");
        System.out.println("4. Виконати завдання");
        System.out.println("5. Переглянути всі проекти та завдання");
        System.out.println("6. Видалити завдання чи проект");
        System.out.println("0. Вийти");
    }

    private static void createProject(Scanner scanner) {
        System.out.print("Введіть назву проекту, який хочете додати: ");
        String projectName = scanner.nextLine();
        projectManager.addProject(new CompositeTask(projectName));
        System.out.println("Проект '" + projectName + "' успішно створено.");
    }

    private static void addTaskToProject(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання, яке хочете додати: ");
            String taskName = scanner.nextLine();

            // Створюємо завдання як CompositeTask з самого початку
            CompositeTask task = new CompositeTask(taskName);
            project.addSubTask(task);  // Додаємо до проєкту
            System.out.println("Завдання '" + taskName + "' додано до проекту '" + projectName + "' як CompositeTask.");
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }

    private static void addSubTaskToTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            System.out.print("Введіть назву завдання: ");
            String taskName = scanner.nextLine();
            Task task = findTaskByName(project, taskName);

            if (task != null) {
                // Оскільки завдання є CompositeTask, додаємо підзавдання
                System.out.print("Введіть назву підзавдання, яке хочете додати: ");
                String subTaskName = scanner.nextLine();
                ((CompositeTask) task).addSubTask(new Task(subTaskName));
                System.out.println("Підзавдання '" + subTaskName + "' додано до завдання '" + taskName + "'.");
            } else {
                System.out.println("Завдання з назвою '" + taskName + "' не знайдено.");
            }
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }

    private static void executeTask(Scanner scanner) {
        System.out.print("Введіть назву проекту: ");
        String projectName = scanner.nextLine();
        CompositeTask project = projectManager.getProjectByName(projectName);

        if (project != null) {
            project.execute();
            System.out.println("Всі завдання в проекті '" + projectName + "' виконано.");
        } else {
            System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
        }
    }

    private static void viewProjects() {
        System.out.println("\n--- Список проектів ---");
        for (CompositeTask project : projectManager.getAllProjects()) {
            System.out.println("- " + project.getName());
            for (Task task : project.getSubTasks()) {
                System.out.println("   - Завдання: " + task.getName() + " (Виконано: " + task.isCompleted() + ")");
            }
        }
    }

    private static void deleteTaskOrProject(Scanner scanner) {
        System.out.print("Введіть назву проекту або завдання: ");
        String name = scanner.nextLine();

        if (projectManager.removeProjectOrTask(name)) {
            System.out.println("Проект або завдання '" + name + "' видалено.");
        } else {
            System.out.println("Проект або завдання з назвою '" + name + "' не знайдено.");
        }
    }

    // Пошук завдання за назвою всередині CompositeTask
    private static Task findTaskByName(CompositeTask compositeTask, String name) {
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
