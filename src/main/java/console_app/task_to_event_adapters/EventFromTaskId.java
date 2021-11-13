package main.java.console_app.task_to_event_adapters;

import main.java.services.event_from_task_creation.EventFromTaskModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class EventFromTaskId implements EventFromTaskModel {

    private final int taskId;
    private final LocalDateTime startTime;
    private final HashSet<String> tags;

    public EventFromTaskId(int taskId, LocalDateTime startTime) {
        this(taskId, startTime, new HashSet<>());
    }

    public EventFromTaskId(int taskId, LocalDateTime startTime, HashSet<String> tags) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.tags = tags;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public HashSet<String> getTags() {
        return tags;
    }

    @Override
    public LocalDate getDates() {
        return startTime.toLocalDate();
    }
}
