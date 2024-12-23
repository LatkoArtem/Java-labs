package com.projectmanagement.menuoptionstests;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.menuoptions.AddTaskToProjectOption;
import com.projectmanagement.tasks.CompositeTask;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskToProjectOptionTest {
    ProjectManager projectManager = new ProjectManager();

    @Test
    void addTaskToProjectTest() {
        String projectName = "Test Project";
        String taskName = "Test Task";
        AddTaskToProjectOption addTaskToProjectOption = new AddTaskToProjectOption(projectManager);

        // Створюємо проект
        CompositeTask project = new CompositeTask(projectName);
        projectManager.addProject(project);

        // Створюємо сканер з рядків
        Scanner scanner = new Scanner(projectName + "\n" + taskName);

        // Додаємо задачу
        addTaskToProjectOption.addTaskToProject(scanner);

        // Перевіряємо, що задача була додана
        assertEquals(taskName, project.getSubTasks().get(0).getName(), "Task name should match");
    }

    @Test
    void addTaskToNonExistentProjectTest() {
        String projectName = "Non-Existent Project";
        String taskName = "Test Task";
        AddTaskToProjectOption addTaskToProjectOption = new AddTaskToProjectOption(projectManager);

        // Створюємо сканер з рядків
        Scanner scanner = new Scanner(projectName + "\n" + taskName);

        // Спробуємо додати задачу до неіснуючого проекту
        addTaskToProjectOption.addTaskToProject(scanner);

        // Перевіряємо, що проект не існує
        assertEquals(0, projectManager.getAllProjects().size(), "No projects should exist");
    }
}
