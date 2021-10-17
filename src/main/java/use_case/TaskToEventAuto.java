package main.java.use_case;
import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskToEventAuto implements TaskToEvent {

    private TaskEventAutoController timeConfirmer;

    public TaskToEventAuto(TaskEventAutoController timeConfirmer) {
        this.timeConfirmer = timeConfirmer;
    }

    /**
     *
     * @param task      the task that needs to be converted into an event
     * @param calendar  the calendar that contains the user's available times
     * @return an Event scheduled at a suggested time,
     *          depending on the available times
     */
    @Override
    public Event createEventFromTask(Task task, Calendar calendar, EventScheduler eventScheduler) {

        List<LocalDateTime> suggestedTimes = new ArrayList<>();
        LocalDateTime availableTime = eventScheduler.getAvailableTime(suggestedTimes, task.getTimeNeeded(), calendar);
        boolean scheduled = confirmTimeWithUser(availableTime);

        suggestedTimes.add(availableTime);

        while (!scheduled) {
            availableTime = eventScheduler.getAvailableTime(suggestedTimes, task.getTimeNeeded(), calendar);
            scheduled = confirmTimeWithUser(availableTime);
            suggestedTimes.add(availableTime);
        }

        return new Event(task, availableTime, availableTime.toLocalTime().plus(task.getTimeNeeded()));
    }

    /**
     * prints the time to the user and receives user input
     * @param time an available time for the event
     * @return  a boolean indicating if the user agrees with the suggested time
     */
    private boolean confirmTimeWithUser(LocalDateTime time) {
        return timeConfirmer.confirmTimeWithUser(time);
    }
}
