package services.task_presentation;

import data_gateway.task.TaskReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskInfoFromTaskReader implements TaskInfo {

    private final TaskReader taskReader;

    public TaskInfoFromTaskReader(TaskReader taskReader) {
        this.taskReader = taskReader;
    }

    @Override
    public long getId() {
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

    @Override
    public boolean getCompleted() {
        return taskReader.getCompleted();
    }
}
