package com.projectmanagement;

import com.projectmanagement.tasks.CompositeTask;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagerTest {

    @Test
    void testProjectManager() {
        ProjectManager manager = new ProjectManager();

        // Створюємо проекти
        CompositeTask project1 = new CompositeTask("Project 1");
        CompositeTask project2 = new CompositeTask("Project 2");

        // Додаємо проекти
        manager.addProject(project1);
        manager.addProject(project2);

        // Перевіряємо, що проекти додані
        List<CompositeTask> allProjects = manager.getAllProjects();
        assertEquals(2, allProjects.size(), "Має бути додано два проекти.");
        assertTrue(allProjects.contains(project1), "Має містити Project 1.");
        assertTrue(allProjects.contains(project2), "Має містити Project 2.");

        // Отримуємо проект за назвою
        CompositeTask retrievedProject = manager.getProjectByName("Project 1");
        assertNotNull(retrievedProject, "Проект має бути знайдений.");
        assertEquals(project1, retrievedProject, "Отриманий проект має відповідати доданому.");

        // Видаляємо проект
        boolean removed = manager.removeProjectOrTask("Project 1");
        assertTrue(removed, "Проект має бути видалений.");
        assertEquals(1, manager.getAllProjects().size(), "Має залишитися один проект.");

        // Додаємо підзавдання до проекту
        CompositeTask subTask = new CompositeTask("Sub Task");
        project2.addSubTask(subTask);

        // Видаляємо підзавдання
        boolean subTaskRemoved = manager.removeProjectOrTask("Sub Task");
        assertTrue(subTaskRemoved, "Підзавдання має бути видалено.");
        assertTrue(project2.getSubTasks().isEmpty(), "Проект не повинен містити підзавдань.");
    }
}

