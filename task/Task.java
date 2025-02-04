package org.lesedibale.task_manager.task;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Task {
    @NonNull private String id;
    @NonNull private String description;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(@NonNull String description, LocalDate dueDate, @NonNull String id) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
        this.id = id;
    }
}
