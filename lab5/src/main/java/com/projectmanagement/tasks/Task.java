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

    public void execute() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return name + (completed ? " [Виконано]" : " [Невиконано]");
    }
}
