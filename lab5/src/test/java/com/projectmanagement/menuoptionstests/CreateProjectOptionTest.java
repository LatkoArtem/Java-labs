package com.projectmanagement.menuoptionstests;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.menuoptions.CreateProjectOption;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateProjectOptionTest {
    ProjectManager projectManager = new ProjectManager();

    @Test
    void createProjectTest() {
        String projectName = "Test Project";
        Scanner scanner = new Scanner(projectName);  // Створюємо сканер з рядка
        CreateProjectOption createProjectOption = new CreateProjectOption(projectManager);

        // Створюємо проект
        createProjectOption.createProject(scanner);

        // Перевіряємо, що проект був створений
        assertEquals(projectName, projectManager.getProjectByName(projectName).getName(), "Project name should match");
    }

    @Test
    void createDuplicateProjectTest() {
        String projectName1 = "Test Project 1";
        Scanner scanner1 = new Scanner(projectName1);  // Створюємо сканер з рядка
        String projectName2 = "Test Project 1";  // Таке ж ім'я для перевірки дубліката
        Scanner scanner2 = new Scanner(projectName2);  // Створюємо сканер з рядка

        CreateProjectOption createProjectOption = new CreateProjectOption(projectManager);

        // Створюємо перший проект
        createProjectOption.createProject(scanner1);

        // Створюємо проект з таким самим іменем
        createProjectOption.createProject(scanner2);

        // Перевіряємо, що проект все одно лише один
        assertEquals(1, projectManager.getAllProjects().size(), "Only one project should exist");
    }
}