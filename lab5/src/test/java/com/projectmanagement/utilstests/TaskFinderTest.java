package com.projectmanagement.utilstests;

import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;
import com.projectmanagement.utils.TaskFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskFinderTest {

    @Test
    void testFindTaskByName() {
        // Створюємо основний CompositeTask
        CompositeTask mainTask = new CompositeTask("Main Task");

        // Додаємо підзавдання
        Task subTask1 = new Task("Sub Task 1");
        Task subTask2 = new Task("Sub Task 2");
        CompositeTask subCompositeTask = new CompositeTask("Composite Sub Task");

        // Додаємо підзавдання в CompositeTask
        mainTask.addSubTask(subTask1);
        mainTask.addSubTask(subTask2);
        mainTask.addSubTask(subCompositeTask);

        // Додаємо підзавдання в Composite Sub Task
        Task nestedTask = new Task("Nested Task");
        subCompositeTask.addSubTask(nestedTask);

        // Перевіряємо пошук задач
        Task foundTask1 = TaskFinder.findTaskByName(mainTask, "Sub Task 1");
        assertNotNull(foundTask1, "Задача 'Sub Task 1' має бути знайдена.");
        assertEquals(subTask1, foundTask1, "Знайдена задача має відповідати 'Sub Task 1'.");

        Task foundTask2 = TaskFinder.findTaskByName(mainTask, "Nested Task");
        assertNotNull(foundTask2, "Задача 'Nested Task' має бути знайдена.");
        assertEquals(nestedTask, foundTask2, "Знайдена задача має відповідати 'Nested Task'.");

        // Перевіряємо пошук неіснуючої задачі
        Task notFoundTask = TaskFinder.findTaskByName(mainTask, "Nonexistent Task");
        assertNull(notFoundTask, "Задача 'Nonexistent Task' не повинна бути знайдена.");
    }
}

