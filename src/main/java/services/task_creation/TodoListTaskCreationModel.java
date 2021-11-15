package services.task_creation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoListTaskCreationModel {

    long getTodoListId();

    String getName();

    Duration getDuration();

    LocalDateTime getDeadline();

    List<String> getSubtasks();
}
