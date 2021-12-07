package services.update_entities;

import java.time.Duration;
import java.time.LocalDateTime;

public interface UpdateTaskBoundary {

    void updateName(long id, String newName);

    void updateDuration(long id, Duration newDuration);

    void updateDeadline(long id, LocalDateTime newDeadline);

    void addSubtask(long id, String subtask);

    void removeSubtask(long id, String subtask);

    void completeTask(long taskId);
}