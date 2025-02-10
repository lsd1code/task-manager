package org.lesedibale.task_manager;

import org.lesedibale.task_manager.cli.TaskCli;
import org.lesedibale.task_manager.persistence.FileStorage;
import org.lesedibale.task_manager.task.Task;

import java.time.LocalDate;
import java.util.List;

public class Application {
    public static void main(String[] args)  {
        new TaskCli().start();
    }
}
