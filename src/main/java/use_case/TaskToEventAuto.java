package main.java.use_case;
import main.java.entity.Calendar;
import main.java.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public class TaskToEventAuto implements TaskToEvent {

    /**
     * Gets an available time slot given task, calendar, and an eventScheduler
     * @param task the Task to be scheduled
     * @param calendar the Calendar where the Task is to be scheduled
     * @param eventScheduler what will be used for this scheduling
     * @param unwantedTimes times that the user does not want
     * @return the time outputted by the eventScheduler
     */
    @Override
    public LocalDateTime getAvailableTime(Task task, Calendar calendar, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes) {
        return eventScheduler.getAvailableTime(unwantedTimes, task.getTimeNeeded(), calendar);
    }
}

