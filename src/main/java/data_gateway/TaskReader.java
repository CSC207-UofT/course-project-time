package main.java.data_gateway;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TaskReader {

    long getId();

    String getName();

    Duration getDuration();

    LocalDateTime getDeadline();

    List<String> getSubtasks();

    boolean getCompleted();

    Set<Duration> getNotificationTimes();
}
