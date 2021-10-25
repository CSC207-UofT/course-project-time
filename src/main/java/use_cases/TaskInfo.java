package main.java.use_cases;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskInfo {

    String getName();
    LocalDateTime getDeadline();
    List<String> getSubtasks();
    boolean getCompleted();

}
