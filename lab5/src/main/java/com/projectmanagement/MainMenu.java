package com.projectmanagement;

import com.projectmanagement.services.*;

import java.util.Scanner;

public class MainMenu {
    private static final ProjectManager projectManager = new ProjectManager();
    private static final ProjectService projectService = new ProjectService(projectManager);
    private static final TaskService taskService = new TaskService(projectManager);

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            MenuPrinter.printMenu();
            System.out.print("Оберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    projectService.createProject(scanner);
                    break;
                case 2:
                    taskService.addTaskToProject(scanner);
                    break;
                case 3:
                    taskService.addSubTaskToTask(scanner);
                    break;
                case 5:
                    projectService.viewProjects();
                    break;
                case 6:
                    System.out.print("Введіть назву проекту для видалення: ");
                    String projectName = scanner.nextLine();
                    projectService.deleteProject(projectName);
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
}
