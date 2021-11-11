package main.java.interface_adapters;

import main.java.use_case.TaskInfo;

import java.time.LocalDateTime;

public interface TaskToEventManualController {
    boolean checkUserSuggestedTime(TaskInfo task, LocalDateTime userSuggestedTime);
}

