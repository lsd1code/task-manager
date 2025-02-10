package org.lesedibale.task_manager.task;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
public class Task {
    @NonNull private String id;
    @NonNull private String description;
    private String dueDate;
    private boolean isCompleted;

    public Task(@NonNull String description, String dueDate, @NonNull String id) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
        this.id = id;
    }
}
