package main.java.use_case;

import java.time.LocalDateTime;
import java.util.List;

public class TaskToEvent implements TaskToEventAuto, TaskToEventManual {

    /**
     * Gets an available time slot given task, calendar, and an eventScheduler
     * @param taskOutputDTO the Task to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param unwantedTimes times that the user does not want
     * @return the time outputted by the eventScheduler
     */
    @Override
    public LocalDateTime getAvailableTime(TaskOutputDTO taskOutputDTO, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes) {
        return eventScheduler.getAvailableTime(unwantedTimes, taskOutputDTO.getDuration());
    }


    /**
     * Checks the availability of a given time
     * @param taskOutputDTO the Task to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param userSuggestedTime times that the user suggested
     * @return whether the time suggested by the user is available
     */
    @Override
    public boolean checkTimeAvailability(TaskOutputDTO taskOutputDTO, EventScheduler eventScheduler, LocalDateTime userSuggestedTime) {
        return eventScheduler.isAvailable(userSuggestedTime.toLocalTime(), taskOutputDTO.getDuration(), userSuggestedTime.toLocalDate());
    }
}

