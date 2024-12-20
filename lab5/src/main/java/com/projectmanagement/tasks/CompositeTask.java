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

    // Рекурсивне виконання всіх підзавдань
    @Override
    public void execute() {
        for (Task task : subTasks) {
            task.execute();
        }
        super.execute();
    }

    // Вивід інформації про завдання та підзавдання
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        for (Task subTask : subTasks) {
            sb.append("\n  - ").append(subTask.toString().replaceAll("(?m)^", "    "));
        }
        return sb.toString();
    }
}
