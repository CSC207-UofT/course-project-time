package services.event_from_task_creation;

import services.task_presentation.TaskInfo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TaskToEvent implements TaskToEventAuto, TaskToEventManual {

    /**
     * Gets an available time slot given task, calendar, and an eventScheduler
     * @param taskInfo the Task to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param unwantedTimes times that the user does not want
     * @return the time outputted by the eventScheduler
     */
    public LocalDateTime getAvailableTime(TaskInfo taskInfo, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes) {
        return eventScheduler.getAvailableTime(unwantedTimes, taskInfo.getDuration()).truncatedTo(ChronoUnit.MINUTES);
    }


    /**
     * Checks the availability of a given time
     * @param taskInfo the Task to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param userSuggestedTime times that the user suggested
     * @return whether the time suggested by the user is available
     */
    public boolean checkTimeAvailability(TaskInfo taskInfo, EventScheduler eventScheduler, LocalDateTime userSuggestedTime) {
        return eventScheduler.isAvailable(userSuggestedTime.toLocalTime(), taskInfo.getDuration(), userSuggestedTime.toLocalDate());
    }
}

