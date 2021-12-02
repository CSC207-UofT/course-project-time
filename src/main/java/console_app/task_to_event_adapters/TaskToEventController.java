package console_app.task_to_event_adapters;

import console_app.event_adapters.EventController;
import services.event_from_task_creation.CalendarAnalyzer;
import services.task_presentation.TaskInfo;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskToEventController {

    private final CalendarAnalyzer eventScheduler;

    protected final EventController eventController;

    public TaskToEventController(EventController eventController, CalendarAnalyzer eventScheduler) {
        this.eventController = eventController;
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
