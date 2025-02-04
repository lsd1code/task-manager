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

    public Task(@NonNull String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
        this.id = this.genId();
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
