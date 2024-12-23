package com.projectmanagement.menuoptionstests;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.menuoptions.AddSubTaskToTaskOption;
import com.projectmanagement.tasks.CompositeTask;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddSubTaskToTaskOptionTest {
    ProjectManager projectManager = new ProjectManager();

    @Test
    void addSubTaskToNonExistentTaskTest() {
        String projectName = "Test Project";
        AddSubTaskToTaskOption addSubTaskToTaskOption = new AddSubTaskToTaskOption(projectManager);

        // Створюємо проект
        CompositeTask project = new CompositeTask(projectName);
        projectManager.addProject(project);

        // Замість Scanner надаємо ввід через ByteArrayInputStream
        String input = "NonExistentTask\nSubTaskName"; // приклад введення
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Додаємо підзадачу до неіснуючої задачі
        addSubTaskToTaskOption.addSubTaskToTask(scanner);

        // Перевіряємо, що підзадача не була додана
        assertEquals(0, project.getSubTasks().size(), "No subtasks should exist");
    }
}
