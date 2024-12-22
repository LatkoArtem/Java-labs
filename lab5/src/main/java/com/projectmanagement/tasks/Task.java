package com.projectmanagement.tasks;

public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Шаблонний метод: визначає послідовність кроків
    public void execute() {
        if (!completed) {
            System.out.println("Виконується завдання: " + name);
            completed = true; // Позначаємо завдання як виконане
            System.out.println("Завдання '" + name + "' виконано");
        } else {
            System.out.println("Завдання '" + name + "' вже виконано.");
        }
    }
}
