package main.java.entity_accessors;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskReader {

    String getName();
    LocalDateTime getDeadline();
    List<String> getSubtasks();
    boolean getCompleted();

}
