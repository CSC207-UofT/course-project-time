package main.java.entity_gateway;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TaskReader {

    int getId();

    String getName();

    Duration getDuration();

    LocalDateTime getDeadline();

    List<String> getSubtasks();

    boolean getCompleted();

    Set<Duration> getNotificationTimes();
}
