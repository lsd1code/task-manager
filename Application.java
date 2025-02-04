package org.lesedibale.task_manager;

import org.lesedibale.task_manager.task.Task;
import org.lesedibale.task_manager.task.TaskManager;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        var taskManager = new TaskManager();

        taskManager.addTask("learn about concurrency", LocalDate.now());
        taskManager.addTask("wash the dishes", LocalDate.now());
        taskManager.addTask("go to training", LocalDate.now());

        taskManager.getTasks().forEach(System.out::println);
    }
}
