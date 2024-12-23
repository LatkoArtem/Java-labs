package com.projectmanagement.taskstests;

import com.projectmanagement.tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testExecute() {
        Task task = new Task("Test Task");

        // Перевіряємо початковий стан
        assertFalse(task.isCompleted(), "Завдання не повинно бути виконане спочатку.");

        // Викликаємо метод execute()
        task.execute();

        // Перевіряємо, що завдання виконане
        assertTrue(task.isCompleted(), "Завдання повинно бути виконане після виконання методу execute().");

        // Викликаємо метод execute() вдруге
        task.execute();

        // Стан завдання не змінюється, воно залишається виконаним
        assertTrue(task.isCompleted(), "Завдання повинно залишатися виконаним після повторного виклику execute().");
    }
}

