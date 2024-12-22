package com.projectmanagement.utils;

import com.projectmanagement.tasks.CompositeTask;
import com.projectmanagement.tasks.Task;

public class TaskFinder {
    public static Task findTaskByName(CompositeTask compositeTask, String name) {
        for (Task task : compositeTask.getSubTasks()) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task;
            }
            if (task instanceof CompositeTask) {
                Task found = findTaskByName((CompositeTask) task, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }
}
