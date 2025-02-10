package org.lesedibale.task_manager.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lesedibale.task_manager.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private final ObjectMapper mapper;
    private final File filepath;

    public FileStorage() {
        mapper = new ObjectMapper();
        filepath = new File("src/main/java/org/lesedibale/task_manager/task_manager.json");
    }

    public boolean save(List<Task> tasks) {
        try {
            mapper.writeValue(filepath, tasks);
            return true;
        } catch (IOException e) {
            System.out.println("Enable to save tasks. Please try again later");
            return false;
        }
    }

    public List<Task> load() {
        try {
            return mapper.readValue(filepath, new TypeReference<List<Task>>() {});
        }
        catch (IOException ignored) {
            return new ArrayList<>();
        }
    }
}
