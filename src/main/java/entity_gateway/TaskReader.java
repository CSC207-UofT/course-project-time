package main.java.entity_gateway;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskReader {

    int getId();

    String getName();

    Duration getDuration();

    LocalDateTime getDeadline();

    List<String> getSubtasks();

}
