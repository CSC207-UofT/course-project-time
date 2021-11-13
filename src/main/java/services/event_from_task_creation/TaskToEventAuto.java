package main.java.services.event_from_task_creation;

import main.java.services.task_presentation.TaskInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskToEventAuto {

    LocalDateTime getAvailableTime(TaskInfo taskInfo, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes);
}
