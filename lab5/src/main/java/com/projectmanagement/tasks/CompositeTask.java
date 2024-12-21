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
    public void addSubTask(Task task) {
        subTasks.add(task);
    }

    // Отримує всі підзавдання
    public List<Task> getSubTasks() {
        return subTasks;
    }

    // Видаляє підзавдання за назвою
    public boolean removeSubTaskByName(String name) {
        return subTasks.removeIf(task -> task.getName().equalsIgnoreCase(name));
    }

    @Override
    public void execute() {
        if (!isCompleted()) {
            System.out.println("Виконується складне завдання: " + getName());
            for (Task subTask : subTasks) {
                subTask.execute(); // Рекурсивно виконуємо підзавдання
            }
            super.execute(); // Позначаємо поточне завдання як виконане
        } else {
            System.out.println("Складне завдання '" + getName() + "' вже виконано.");
        }
    }
}
