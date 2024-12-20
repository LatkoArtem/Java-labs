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
        System.out.println("6. Видалити підзавдання, завдання чи проект");
        System.out.println("0. Вийти");
    }

    private static void createProject(Scanner scanner) {
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
            System.out.println("Завдання '" + taskName + "' додано до проекту '" + projectName);
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
            // Виводимо назву проєкту
            System.out.println("- Проект: " + project.getName());

            // Виводимо завдання проєкту
            for (Task task : project.getSubTasks()) {
                printTasks(task, 1, false); // Передаємо "isSubtask" як false
            }
        }
    }

    // Рекурсивний метод для виводу завдань та підзавдань
    private static void printTasks(Task task, int indentLevel, boolean isSubtask) {
        String indent = "   ".repeat(indentLevel); // Створюємо відступ для підзавдань

        // Виводимо "Завдання" або "Підзавдання" залежно від прапорця isSubtask
        String taskType = isSubtask ? "Підзавдання" : "Завдання";
        System.out.println(indent + taskType + ": " + task.getName() + " (Виконано: " + task.isCompleted() + ")");

        // Якщо це CompositeTask, рекурсивно виводимо підзавдання
        if (task instanceof CompositeTask compositeTask) {
            for (Task subTask : compositeTask.getSubTasks()) {
                printTasks(subTask, indentLevel + 1, true); // Позначаємо всі вкладені як підзавдання
            }
        }
    }

    private static void deleteTaskOrProject(Scanner scanner) {
        System.out.println("\nЩо ви хочете видалити?");
        System.out.println("1. Проект");
        System.out.println("2. Завдання");
        System.out.println("3. Підзавдання");
        System.out.print("Оберіть опцію: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Зчитуємо символ нового рядка

        switch (choice) {
            case 1: // Видалення проєкту
                System.out.print("Введіть назву проекту: ");
                String projectName = scanner.nextLine();
                if (projectManager.removeProjectOrTask(projectName)) {
                    System.out.println("Проект '" + projectName + "' видалено.");
                } else {
                    System.out.println("Проект з назвою '" + projectName + "' не знайдено.");
                }
                break;

            case 2: // Видалення завдання
                System.out.print("Введіть назву проекту, в якому знаходиться завдання: ");
                String parentProjectName = scanner.nextLine();
                CompositeTask project = projectManager.getProjectByName(parentProjectName);

                if (project != null) {
                    System.out.print("Введіть назву завдання: ");
                    String taskName = scanner.nextLine();
                    if (deleteTaskFromComposite(project, taskName)) {
                        System.out.println("Завдання '" + taskName + "' видалено з проекту '" + parentProjectName + "'.");
                    } else {
                        System.out.println("Завдання з назвою '" + taskName + "' не знайдено в проекті '" + parentProjectName + "'.");
                    }
                } else {
                    System.out.println("Проект з назвою '" + parentProjectName + "' не знайдено.");
                }
                break;

            case 3: // Видалення підзавдання
                System.out.print("Введіть назву проекту, в якому знаходиться підзавдання: ");
                String projectForSubTaskName = scanner.nextLine();
                CompositeTask parentProject = projectManager.getProjectByName(projectForSubTaskName);

                if (parentProject != null) {
                    System.out.print("Введіть назву завдання, в якому знаходиться підзавдання: ");
                    String parentTaskName = scanner.nextLine();
                    Task parentTask = findTaskByName(parentProject, parentTaskName);

                    if (parentTask instanceof CompositeTask) {
                        System.out.print("Введіть назву підзавдання: ");
                        String subTaskName = scanner.nextLine();
                        if (deleteTaskFromComposite((CompositeTask) parentTask, subTaskName)) {
                            System.out.println("Підзавдання '" + subTaskName + "' видалено з завдання '" + parentTaskName + "'.");
                        } else {
                            System.out.println("Підзавдання з назвою '" + subTaskName + "' не знайдено в завданні '" + parentTaskName + "'.");
                        }
                    } else {
                        System.out.println("Завдання з назвою '" + parentTaskName + "' не є складовим завданням.");
                    }
                } else {
                    System.out.println("Проект з назвою '" + projectForSubTaskName + "' не знайдено.");
                }
                break;

            default:
                System.out.println("Невірна опція. Спробуйте ще раз.");
        }
    }

    private static boolean deleteTaskFromComposite(CompositeTask parent, String taskName) {
        // Ітерація через список підзавдань
        for (Task task : parent.getSubTasks()) {
            // Якщо знайдено завдання з відповідною назвою, видаляємо його
            if (task.getName().equals(taskName)) {
                parent.getSubTasks().remove(task);
                return true;
            }

            // Якщо завдання є CompositeTask, рекурсивно шукаємо в ньому
            if (task instanceof CompositeTask) {
                boolean deleted = deleteTaskFromComposite((CompositeTask) task, taskName);
                if (deleted) {
                    return true;
                }
            }
        }

        // Якщо завдання не знайдено
        return false;
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
