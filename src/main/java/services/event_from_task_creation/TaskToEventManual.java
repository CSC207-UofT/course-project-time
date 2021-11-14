package main.java.services.event_from_task_creation;

import main.java.services.task_presentation.TaskInfo;

import java.time.LocalDateTime;

public interface TaskToEventManual {

    boolean checkTimeAvailability(TaskInfo taskInfo, EventScheduler eventScheduler, LocalDateTime userSuggestedTime);
}
