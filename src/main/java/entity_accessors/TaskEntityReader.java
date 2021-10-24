package main.java.entity_accessors;

import main.java.entities.Task;

import java.time.LocalDateTime;
import java.util.List;

public class TaskEntityReader implements TaskReader {

    private final Task task;

    public TaskEntityReader(Task task) {
        this.task = task;
    }

    @Override
    public String getName() {
        return task.getTaskName();
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
