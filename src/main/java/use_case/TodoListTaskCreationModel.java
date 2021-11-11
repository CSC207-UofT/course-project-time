package main.java.use_case;

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
