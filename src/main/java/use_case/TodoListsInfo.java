package main.java.use_case;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoListsInfo {
    String getName(int todoListId, int taskId);

    LocalDateTime getDeadline(int todoListId, int taskId);

    Duration getDuration(int todoListId, int taskId);

    List<String> getSubtasks(int todoListId, int taskId);

    List<TaskInfo> getAllTasks();
}
