package gui.model.DTO;

import services.task_creation.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskCreationDTO implements TodoListTaskCreationModel {
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
}
