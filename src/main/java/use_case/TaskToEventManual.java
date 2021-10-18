package main.java.use_case;

import main.java.controller.TaskToEventManualController;
import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;

import java.time.LocalDateTime;

public class TaskToEventManual {

    private TaskToEventManualController timeAsker;

    public TaskToEventManual(TaskToEventManualController timeAsker) {
        this.timeAsker = timeAsker;
    }

    /**
     *
     * @param task      the task that needs to be converted into an event
     * @param calendar  the calendar that contains the user's available times
     * @return an Event scheduled at a user suggested time
     */
    public Event createEventFromTask(Task task, Calendar calendar, EventScheduler eventScheduler) {
        LocalDateTime userSuggestedTime = getUserSuggestedTime();
        boolean validTime = eventScheduler.checkAvailability(userSuggestedTime, calendar, task.getTimeNeeded());
        while (!validTime) {
            userSuggestedTime = getUserSuggestedTime();
            validTime = eventScheduler.checkAvailability(userSuggestedTime, calendar, task.getTimeNeeded());
        }
        return new Event(task, userSuggestedTime, userSuggestedTime.toLocalTime().plus(task.getTimeNeeded()));
    }

    /**
     * get user to input a time to schedule the event
     * @return the time suggested by the user as a LocalDateTime instance
     */
    private LocalDateTime getUserSuggestedTime() {
        return timeAsker.getUserSuggestedTime();
    }
}
