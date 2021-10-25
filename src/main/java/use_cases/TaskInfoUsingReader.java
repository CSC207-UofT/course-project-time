package main.java.use_cases;

import main.java.entity_accessors.TaskReader;

import java.time.LocalDateTime;
import java.util.List;

public class TaskInfoUsingReader implements TaskInfo {

    private final TaskReader taskReader;

    public TaskInfoUsingReader(TaskReader taskReader) {
        this.taskReader = taskReader;
    }

    @Override
    public String getName() {
        return taskReader.getName();
    }

    @Override
    public LocalDateTime getDeadline() {
        return taskReader.getDeadline();
    }

    @Override
    public List<String> getSubtasks() {
        return taskReader.getSubtasks();
    }

    @Override
    public boolean getCompleted() {
        return taskReader.getCompleted();
    }
}
