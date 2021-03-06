package services.taskcreation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoListTaskCreationModel {

    String getName();

    Duration getDuration();

    LocalDateTime getDeadline();

    List<String> getSubtasks();
}
