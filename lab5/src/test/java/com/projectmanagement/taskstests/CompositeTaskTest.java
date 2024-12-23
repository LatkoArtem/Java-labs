package com.projectmanagement.taskstests;

import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompositeTaskTest {

    @Test
    void testAddSubTask() {
        CompositeTask compositeTask = new CompositeTask("Main Task");
        Task subTask = new Task("Sub Task 1");

        assertTrue(compositeTask.addSubTask(subTask), "Підзавдання має бути додано успішно.");
        assertEquals(1, compositeTask.getSubTasks().size(), "Розмір списку підзавдань має бути 1.");
        assertEquals("Sub Task 1", compositeTask.getSubTasks().get(0).getName(), "Назва підзавдання має співпадати.");
    }

    @Test
    void testAddDuplicateSubTask() {
        CompositeTask compositeTask = new CompositeTask("Main Task");
        Task subTask = new Task("Sub Task 1");

        compositeTask.addSubTask(subTask);
        assertFalse(compositeTask.addSubTask(new Task("Sub Task 1")), "Дубльоване підзавдання не повинно бути додано.");
        assertEquals(1, compositeTask.getSubTasks().size(), "Розмір списку підзавдань має залишатися 1.");
    }

    @Test
    void testRemoveSubTaskByName() {
        CompositeTask compositeTask = new CompositeTask("Main Task");
        Task subTask = new Task("Sub Task 1");

        compositeTask.addSubTask(subTask);
        assertTrue(compositeTask.removeSubTaskByName("Sub Task 1"), "Підзавдання має бути успішно видалено.");
        assertEquals(0, compositeTask.getSubTasks().size(), "Список підзавдань має бути порожнім.");
    }

    @Test
    void testAreAllSubTasksCompleted() {
        CompositeTask compositeTask = new CompositeTask("Main Task");
        Task subTask1 = new Task("Sub Task 1");
        Task subTask2 = new Task("Sub Task 2");

        compositeTask.addSubTask(subTask1);
        compositeTask.addSubTask(subTask2);

        assertFalse(compositeTask.areAllSubTasksCompleted(), "Не всі підзавдання виконані.");

        subTask1.execute();
        subTask2.execute();

        assertTrue(compositeTask.areAllSubTasksCompleted(), "Усі підзавдання мають бути виконані.");
    }

    @Test
    void testExecute() {
        CompositeTask compositeTask = new CompositeTask("Main Task");
        Task subTask1 = new Task("Sub Task 1");
        Task subTask2 = new Task("Sub Task 2");

        compositeTask.addSubTask(subTask1);
        compositeTask.addSubTask(subTask2);

        // Переконуємося, що підзавдання не виконані
        assertFalse(subTask1.isCompleted(), "Sub Task 1 не має бути виконаним.");
        assertFalse(subTask2.isCompleted(), "Sub Task 2 не має бути виконаним.");

        // Викликаємо execute() для CompositeTask
        compositeTask.execute();

        // Перевіряємо, що підзавдання виконані
        assertTrue(subTask1.isCompleted(), "Sub Task 1 має бути виконаним.");
        assertTrue(subTask2.isCompleted(), "Sub Task 2 має бути виконаним.");

        // Перевіряємо, що CompositeTask теж виконано
        assertTrue(compositeTask.isCompleted(), "Main Task має бути виконаним.");
    }

}
