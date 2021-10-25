package main.java.use_case;

import main.java.entity_gateway.TaskReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskInfoFromTaskReader implements TaskInfo {

    private TaskReader taskReader;

    public TaskInfoFromTaskReader(TaskReader taskReader) {
        this.taskReader = taskReader;
    }

    @Override
    public int getId() {
        return taskReader.getId();
    }

    @Override
    public String getName() {
        return taskReader.getName();
    }

    @Override
    public Duration getDuration() {
        return taskReader.getDuration();
    }

    @Override
    public LocalDateTime getDeadline() {
        return taskReader.getDeadline();
    }

    @Override
    public List<String> getSubtasks() {
        return taskReader.getSubtasks();
    }
}
