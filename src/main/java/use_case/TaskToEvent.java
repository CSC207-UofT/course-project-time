package main.java.use_case;
import main.java.entity.Calendar;
import main.java.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public class TaskToEvent implements TaskToEventAuto, TaskToEventManual {

    /**
     * Gets an available time slot given task, calendar, and an eventScheduler
     * @param task the Task to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param unwantedTimes times that the user does not want
     * @return the time outputted by the eventScheduler
     */
    @Override
    public LocalDateTime getAvailableTime(Task task, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes) {
        return eventScheduler.getAvailableTime(unwantedTimes, task.getTimeNeeded());
    }


    /**
     * Checks the availability of a given time
     * @param task the Task to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param userSuggestedTime times that the user suggested
     * @return whether the time suggested by the user is available
     */
    @Override
    public boolean checkTimeAvailability(Task task, EventScheduler eventScheduler, LocalDateTime userSuggestedTime) {
        return eventScheduler.isAvailable(userSuggestedTime.toLocalTime(), task.getTimeNeeded(), userSuggestedTime.toLocalDate());
    }
}

