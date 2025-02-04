package org.lesedibale.task_manager.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> dataStore;
    private static long id;


    public TaskManager() {
        dataStore = new ArrayList<>();
        id = 1;
    }

    public boolean addTask(String description, LocalDate dueDate) {
        var task = new Task(description, dueDate, String.valueOf(id++));
        dataStore.add(task);

        return true;
    }

    public List<Task> getTasks() {
        return this.dataStore;
    }

    public boolean markCompleted(String taskId) {
        return dataStore
                .stream()
                .filter(t -> t.getId().equalsIgnoreCase(taskId))
                .findFirst()
                .map(t-> {
                    t.setCompleted(true);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteTask(String taskId) {
        return dataStore.removeIf(task -> task.getId().equalsIgnoreCase(taskId));
    }

    public boolean contains(String taskId) {
        return dataStore.stream().anyMatch(t -> t.getId().equalsIgnoreCase(taskId));
    }

    private String genId() {
        var stringBuilder = new StringBuilder();
        var length = 5;

        for (int i = 0; i < length; i++) {
            var randomDigit = (int) (Math.random() * 10);
            stringBuilder.append(randomDigit);
        }

        return stringBuilder.toString();
    }
}
