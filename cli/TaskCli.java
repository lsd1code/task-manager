package org.lesedibale.task_manager.cli;

import org.lesedibale.task_manager.task.Task;
import org.lesedibale.task_manager.task.TaskManager;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskCli {
    private final TaskManager taskManager;

    public TaskCli() {
        taskManager = new TaskManager();
    }

    public void displayCli() {
        while (true) {
            printMenu();

            int choice;
            try {
                choice = getInt(">");
            } catch (InputMismatchException e) {
                break;
            }

            switch (choice) {
                case 1 -> addNewTask();
                case 2 -> viewAllTasks();
                case 3 -> markTaskAsCompleted();
                case 4 -> deleteTask();
                case 5 -> {
                    System.out.println("""
                    Thanks for using our program <3
                    """);
                    break;
                }
            }
        }
    }

    public void addNewTask() {
        System.out.println("Add New Task");
        var desc = getValue("Description:");

        LocalDate validDate = null;

        try {
            var year = getInt("year: ");
            var month = getInt("month: ");
            var day = getInt("day: ");

            if(year < 2025) {
                throw new DateTimeException("Year cannot be less than 2025");
            }

            validDate = LocalDate.of(year, month, day);
        } catch (InputMismatchException e) {
            System.out.println("Error: Enter a valid input");
        } catch (DateTimeException e) {
            System.out.println("Error: enter a valid date");
        }

        var task = taskManager.addTask(desc, validDate);

        if (task) {
            System.out.println("Task added successfully");
        } else {
            System.out.println("Something went wrong. Please try again!");
        }
    }

    public void viewAllTasks() {
        var tasks = taskManager.getTasks();

        if(tasks.isEmpty()) {
            System.out.println("You currently have no tasks");
            return;
        }

        var allTasks = tasks.size();
        var completedTasks = tasks.stream().filter(Task::isCompleted).toList().size();

        System.out.println("All Tasks: " + "[" + allTasks + "]");
        System.out.println("Completed Tasks: " + "[" + completedTasks + "]");
        System.out.printf("| %-10s | %-20s | %-10s | %-10s | \n", "Task ID", "Description", "Due Date", "Completed");

        tasks.forEach(task ->
                System.out.printf(
                        "| %-10s | %-20s | %-10s | %-10s | \n",
                        task.getId(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.isCompleted()
                )
        );
    }

    public void markTaskAsCompleted() {
        var taskId = getValue("Enter Task ID of a Task you want to mark as completed:");
        var exists = taskManager.contains(taskId);

        if (!exists) {
            System.out.println("task not found");
            return;
        }

        taskManager.markCompleted(taskId);
        System.out.println("Task Marked As Completed");
    }

    public void deleteTask() {
        var taskId = getValue("Enter Task ID:");
        var exists = taskManager.contains(taskId);

        if (!exists) {
            System.out.println("task not found");
            return;
        }

        taskManager.deleteTask(taskId);
        System.out.println("Task Removed Successfully");
    }

    private void printMenu() {
        String mainMenu = """
        
        1. Add New Task
        2. List All Tasks
        3. Mark Task As Completed
        4. Remove Task
        5. Exit Program
        """;

        System.out.println(mainMenu);
    }

    public String getValue(String prompt) {
        Scanner scn = new Scanner(System.in);
        System.out.print(prompt + " ");
        return scn.nextLine();
    }

    public int getInt(String prompt) throws InputMismatchException {
        Scanner scn = new Scanner(System.in);
        System.out.print(prompt + " ");
        return scn.nextInt();
    }
}
