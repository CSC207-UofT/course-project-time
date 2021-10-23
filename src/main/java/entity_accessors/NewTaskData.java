package main.java.entity_accessors;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class NewTaskData {
    public final String taskName;
    public final Duration timeNeeded;
    public final LocalDateTime deadline;
    public final List<String> subTasks;

    public NewTaskData(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        this.taskName = taskName;
        this.timeNeeded = timeNeeded;
        this.deadline = deadline;
        this.subTasks = subTasks;
    }
}
