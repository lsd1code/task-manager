package org.lesedibale.task_manager.task;

import org.lesedibale.task_manager.persistence.FileStorage;

import java.time.LocalDate;
import java.util.List;

public class TaskManager {
    private final List<Task> dataStore;
    private static long id;
    private final FileStorage fileStorage;


    public TaskManager(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
        dataStore = fileStorage.load();
        id = 1;
    }

    public boolean addTask(String description, LocalDate dueDate) {
        dataStore.add(new Task(description, String.valueOf(dueDate), String.valueOf(id++)));
        fileStorage.save(dataStore);

        return true;
    }

    public List<Task> getTasks() {
        return this.dataStore;
    }

    public boolean markCompleted(String taskId) {
        var result = dataStore
                .stream()
                .filter(t -> t.getId().equalsIgnoreCase(taskId))
                .findFirst()
                .map(t-> {
                    t.setCompleted(true);
                    return true;
                })
                .orElse(false);

        fileStorage.save(dataStore);

        return result;
    }

    public boolean deleteTask(String taskId) {
        var result = dataStore.removeIf(task -> task.getId().equalsIgnoreCase(taskId));
        fileStorage.save(dataStore);

        return result;
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
