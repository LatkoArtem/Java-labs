package com.projectmanagement.menuoptions;

import com.projectmanagement.ProjectManager;
import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;

public class ViewProjectsOption {
    private final ProjectManager projectManager;

    public ViewProjectsOption(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public void viewProjects() {
        System.out.println("\n--- Список проектів ---");
        if (projectManager.getAllProjects().isEmpty()) {
            System.out.println("Наразі жодного проекту не створено.");
            return;
        }
        for (CompositeTask project : projectManager.getAllProjects()) {
            System.out.println("- Проект: " + project.getName());
            for (Task task : project.getSubTasks()) {
                printTasks(task, 1, false);
            }
        }
    }

    private void printTasks(Task task, int indentLevel, boolean isSubtask) {
        String indent = "   ".repeat(indentLevel);
        String taskType = isSubtask ? "Підзавдання" : "Завдання";
        System.out.println(indent + taskType + ": " + task.getName() + " (Виконано: " + task.isCompleted() + ")");

        if (task instanceof CompositeTask compositeTask) {
            for (Task subTask : compositeTask.getSubTasks()) {
                printTasks(subTask, indentLevel + 1, true);
            }
        }
    }
}
