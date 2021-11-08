package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class EventFromTaskId implements EventFromTaskDTO {

    private final int taskId;
    private final LocalDateTime startTime;
    private final Set<String> tags;

    public EventFromTaskId(int taskId, LocalDateTime startTime) {
        this(taskId, startTime, new HashSet<>());
    }

    public EventFromTaskId(int taskId, LocalDateTime startTime, Set<String> tags) {
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
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public LocalDate getDates() {
        return startTime.toLocalDate();
    }
}
