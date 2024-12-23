package com.projectmanagement.tasks;

import java.util.ArrayList;
import java.util.List;

public class CompositeTask extends Task {
    private final List<Task> subTasks;

    public CompositeTask(String name) {
        super(name);
        this.subTasks = new ArrayList<>();
    }

    // Додає підзавдання
    public boolean addSubTask(Task task) {
        if (subTasks.stream().anyMatch(t -> t.getName().equalsIgnoreCase(task.getName()))) {
            System.out.println("Підзавдання з такою назвою вже існує.");
            return false;
        }
        subTasks.add(task);
        return true;
    }

    // Отримує всі підзавдання
    public List<Task> getSubTasks() {
        return subTasks;
    }

    // Видаляє підзавдання за назвою
    public boolean removeSubTaskByName(String name) {
        return subTasks.removeIf(task -> task.getName().equalsIgnoreCase(name));
    }

    // Перевірка, чи всі підзавдання виконані
    public boolean areAllSubTasksCompleted() {
        return subTasks.stream().allMatch(Task::isCompleted);
    }

    @Override
    public void execute() {
        if (!isCompleted()) {
            // Виконуємо всі підзавдання
            for (Task subTask : subTasks) {
                if (!subTask.isCompleted()) {
                    subTask.execute(); // Рекурсивно виконуємо підзавдання
                }
            }

            // Якщо всі підзавдання виконані, позначаємо завдання як виконане
            if (areAllSubTasksCompleted()) {
                super.execute(); // Позначаємо поточне завдання як виконане
            }
        } else {
            System.out.println("Завдання '" + getName() + "' вже виконано.");
        }
    }
}
