package org.lesedibale.task_manager.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final List<Task> dataStore;

    public TaskManager() {
        dataStore = new ArrayList<>();
    }

    public boolean addTask(String description, LocalDate dueDate) {
        var task = new Task(description, dueDate, genId());
        dataStore.add(task);

        return true;
    }

    public List<Task> getTasks() {
        return this.dataStore;
    }

    private String genId() {
        var stringBuilder = new StringBuilder();
        var length = 5;

        for(int i = 0; i < length; i++) {
            var randomDigit = (int) (Math.random() * 10);
            stringBuilder.append(randomDigit);
        }

        return stringBuilder.toString();
    }
}
