package consoleapp.tasktoeventadapters;

import services.eventfromtaskcreation.CalendarAnalyzer;
import services.taskpresentation.TaskInfo;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskToEventController {

    private final CalendarAnalyzer eventScheduler;

    public TaskToEventController(CalendarAnalyzer eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    /**
     * Check whether the time suggested by the user is available
     * @param task the task to be scheduled to event
     * @param userSuggestedTime the time suggested by the user
     * @return whether the task is successfully scheduled to event
     */
    public boolean checkUserSuggestedTime(TaskInfo task, LocalDateTime userSuggestedTime) {
        return eventScheduler.isAvailable(userSuggestedTime.toLocalTime(), task.getDuration(), userSuggestedTime.toLocalDate());
    }

    public LocalDateTime getSuggestedTime(Duration duration) {
        return eventScheduler.getAvailableTime(duration);
    }
}
