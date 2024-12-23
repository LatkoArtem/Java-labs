package com.projectmanagement.menuoptionstests;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.menuoptions.ViewProjectsOption;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewProjectsOptionTest {

    @Test
    void testViewProjectsWhenNoProjects() {
        // Створюємо реальний ProjectManager, який не має проектів
        ProjectManager projectManager = new ProjectManager();

        // Створюємо екземпляр ViewProjectsOption
        ViewProjectsOption viewProjectsOption = new ViewProjectsOption(projectManager);

        // Перехоплюємо виведення на консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Викликаємо метод
        viewProjectsOption.viewProjects();

        // Перевіряємо, чи виведено повідомлення про відсутність проектів
        String expectedOutput = "\n--- Список проектів ---\nНаразі жодного проекту не створено.\n";
        assertEquals(expectedOutput, outContent.toString().replace("\r", "")); // Видалення зайвих символів
    }

    @Test
    void testViewProjectsWithProjects() {
        // Створюємо реальний ProjectManager та додаємо проект
        ProjectManager projectManager = new ProjectManager();
        CompositeTask project = new CompositeTask("Test Project");
        Task task = new Task("Test Task");
        project.addSubTask(task);  // Додаємо завдання до проекту
        projectManager.addProject(project);  // Додаємо проект в менеджер

        // Створюємо екземпляр ViewProjectsOption
        ViewProjectsOption viewProjectsOption = new ViewProjectsOption(projectManager);

        // Перехоплюємо виведення на консоль
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Викликаємо метод
        viewProjectsOption.viewProjects();

        // Перевіряємо, чи виведено правильне повідомлення про проект та завдання
        String expectedOutput = "\n--- Список проектів ---\n- Проект: Test Project\n   Завдання: Test Task (Виконано: false)\n";
        assertEquals(expectedOutput, outContent.toString().replace("\r", ""));
    }

}
