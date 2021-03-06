package datagateway.task;

import entity.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskToTaskReader implements TaskReader{

    private final Task task;

    public TaskToTaskReader(Task task) {
        this.task = task;
    }

    @Override
    public long getId() {
        return task.getId();
    }

    @Override
    public String getName() {
        return task.getTaskName();
    }

    @Override
    public Duration getDuration() {
        return task.getTimeNeeded();
    }

    @Override
    public LocalDateTime getDeadline() {
        return task.getDeadline();
    }

    @Override
    public List<String> getSubtasks() {
        return task.getSubTasks();
    }

    @Override
    public boolean getCompleted() {
        return task.getCompleted();
    }
}
