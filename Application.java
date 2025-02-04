package org.lesedibale.task_manager;

import org.lesedibale.task_manager.cli.TaskCli;
import org.lesedibale.task_manager.task.Task;
import org.lesedibale.task_manager.task.TaskManager;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        new TaskCli().displayCli();
    }
}
