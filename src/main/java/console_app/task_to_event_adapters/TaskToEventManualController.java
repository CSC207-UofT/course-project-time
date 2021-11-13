package main.java.console_app.task_to_event_adapters;

import main.java.services.task_presentation.TaskInfo;

import java.time.LocalDateTime;

public interface TaskToEventManualController {
    boolean checkUserSuggestedTime(TaskInfo task, LocalDateTime userSuggestedTime);
}
