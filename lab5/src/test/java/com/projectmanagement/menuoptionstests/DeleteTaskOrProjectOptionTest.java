package com.projectmanagement.menuoptionstests;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.menuoptions.DeleteTaskOrProjectOption;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskOrProjectOptionTest {

    @Test
    void deleteProjectTest() {
        ProjectManager projectManager = new ProjectManager();
        DeleteTaskOrProjectOption deleteTaskOrProjectOption = new DeleteTaskOrProjectOption(projectManager);
        String projectName = "Test Project";

        // Створення проекту
        projectManager.addProject(new CompositeTask(projectName));

        // Перевірка чи проект можна видалити
        deleteTaskOrProjectOption.deleteProject(new Scanner("Test Project"));

        // Перевірка, чи проект видалений
        assertNull(projectManager.getProjectByName(projectName), "Проект не був видалений.");
    }

    @Test
    void deleteProjectNotFoundTest() {
        ProjectManager projectManager = new ProjectManager();
        DeleteTaskOrProjectOption deleteTaskOrProjectOption = new DeleteTaskOrProjectOption(projectManager);
        String projectName = "NonExistentProject";

        // Спроба видалити неіснуючий проект
        deleteTaskOrProjectOption.deleteProject(new Scanner("NonExistentProject"));

        // Перевірка, чи проект не був знайдений
        assertNull(projectManager.getProjectByName(projectName), "Проект має бути відсутнім.");
    }

    @Test
    void deleteTaskNotFoundTest() {
        ProjectManager projectManager = new ProjectManager();
        DeleteTaskOrProjectOption deleteTaskOrProjectOption = new DeleteTaskOrProjectOption(projectManager);
        String projectName = "Test Project";
        String taskName = "NonExistentTask";

        // Створення проекту
        CompositeTask project = new CompositeTask(projectName);
        projectManager.addProject(project);

        // Спроба видалити неіснуюче завдання
        deleteTaskOrProjectOption.deleteTask(new Scanner("Test Project\nNonExistentTask"));

        // Перевірка, чи завдання не було знайдено
        assertTrue(project.getSubTasks().isEmpty(), "Завдання має бути відсутнім.");
    }
}
