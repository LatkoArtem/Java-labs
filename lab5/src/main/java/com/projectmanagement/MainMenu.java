package com.projectmanagement;

import com.projectmanagement.menuoptions.*;

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
                    new CreateProjectOption(projectManager).createProject(scanner);
                    break;
                case 2:
                    new AddTaskToProjectOption(projectManager).addTaskToProject(scanner);
                    break;
                case 3:
                    new AddSubTaskToTaskOption(projectManager).addSubTaskToTask(scanner);
                    break;
                case 4:
                    new ExecuteTaskOption(projectManager).executeTask(scanner);
                    break;
                case 5:
                    new ViewProjectsOption(projectManager).viewProjects();
                    break;
                case 6:
                    new DeleteTaskOrProjectOption(projectManager).deleteTaskOrProject(scanner);
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
        System.out.println("6. Видалити підзавдання, завдання чи проект");
        System.out.println("0. Вийти");
    }
}