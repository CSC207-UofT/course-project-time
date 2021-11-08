package main.java.interface_adapters;

import main.java.use_case.TaskOutputDTO;

import java.time.LocalDateTime;

public interface TaskToEventManualController {
    boolean checkUserSuggestedTime(TaskOutputDTO task, LocalDateTime userSuggestedTime);
}

