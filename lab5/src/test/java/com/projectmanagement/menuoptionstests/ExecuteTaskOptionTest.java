package com.projectmanagement.menuoptionstests;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.menuoptions.ExecuteTaskOption;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExecuteTaskOptionTest {

    @Test
    void testExecuteTaskWithExistingProjectAndTask() {
        // Створюємо проект і завдання вручну
        CompositeTask project = new CompositeTask("Test Project");
        Task task = new Task("Test Task");
        project.addSubTask(task);  // Додаємо завдання до проекту

        // Створюємо ProjectManager, щоб повертати створений проект
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject(project);

        // Створюємо клас ExecuteTaskOption
        ExecuteTaskOption executeTaskOption = new ExecuteTaskOption(projectManager);

        // Створюємо Scanner, який буде повертати певне значення
        Scanner scanner = new Scanner("Test Project\nTest Task\n");

        // Викликаємо метод executeTask
        executeTaskOption.executeTask(scanner);

        // Перевіряємо, що завдання було виконано
        assertTrue(task.isCompleted(), "Завдання не було виконано.");
    }

    @Test
    void testExecuteTaskWithExistingProjectAndNoTask() {
        // Створюємо проект вручну
        CompositeTask project = new CompositeTask("Test Project");

        // Створюємо ProjectManager, щоб повертати створений проект
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject(project);

        // Створюємо клас ExecuteTaskOption
        ExecuteTaskOption executeTaskOption = new ExecuteTaskOption(projectManager);

        // Створюємо Scanner, який буде повертати певне значення
        Scanner scanner = new Scanner("Test Project\n");

        // Викликаємо метод executeTask
        executeTaskOption.executeTask(scanner);

        // Перевіряємо, що проект був виконаний
        assertTrue(project.isCompleted(), "Проект не був виконаний.");
    }

    @Test
    void testExecuteTaskWithNonExistingProject() {
        // Створюємо ProjectManager, не додаючи жодного проекту
        ProjectManager projectManager = new ProjectManager();

        // Створюємо клас ExecuteTaskOption
        ExecuteTaskOption executeTaskOption = new ExecuteTaskOption(projectManager);

        // Створюємо Scanner, який буде повертати певне значення
        Scanner scanner = new Scanner("Nonexistent Project\n");

        // Викликаємо метод executeTask
        executeTaskOption.executeTask(scanner);

        // В даному тесті ми перевіряємо, що повідомлення про відсутність проекту виводиться
        // У реальному коді можна перевіряти виведення в консоль або логування
    }
}
