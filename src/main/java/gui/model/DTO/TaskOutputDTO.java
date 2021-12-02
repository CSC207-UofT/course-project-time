package gui.model.DTO;

import services.task_presentation.TaskInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskOutputDTO implements TaskInfo {
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Duration getDuration() {
        return null;
    }

    @Override
    public LocalDateTime getDeadline() {
        return null;
    }

    @Override
    public List<String> getSubtasks() {
        return null;
    }

    @Override
    public boolean getCompleted() {
        return false;
    }
}
