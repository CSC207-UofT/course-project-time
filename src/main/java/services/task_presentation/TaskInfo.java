package main.java.services.task_presentation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskInfo {

    long getId();

    String getName();

    Duration getDuration();

    LocalDateTime getDeadline();

    List<String> getSubtasks();

    boolean getCompleted();
}
