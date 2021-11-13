package main.java.console_app.task_to_event_adapters;

import main.java.console_app.event_adapters.EventController;
import main.java.services.event_from_task_creation.EventFromTaskCreatorBoundary;
import main.java.services.event_from_task_creation.EventFromTaskModel;
import main.java.services.event_from_task_creation.EventScheduler;
import main.java.services.event_from_task_creation.TaskToEvent;
import main.java.services.task_presentation.TaskInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskToEventController implements TaskToEventAutoController, TaskToEventManualController {

    private final TaskToEvent taskToEvent = new TaskToEvent();
    private final EventFromTaskCreatorBoundary eventFromTaskCreatorBoundary;
    private final EventScheduler eventScheduler;

    protected final EventController eventController;

    public TaskToEventController(EventController eventController, EventFromTaskCreatorBoundary eventFromTaskBoundary, EventScheduler eventScheduler) {
        this.eventFromTaskCreatorBoundary = eventFromTaskBoundary;
        this.eventController = eventController;
        this.eventScheduler = eventScheduler;
    }

    public boolean createEventFromTask(int taskId, LocalDateTime startTime) {
        EventFromTaskModel eventData = new EventFromTaskId(taskId, startTime);
        return eventFromTaskCreatorBoundary.createEventFromTask(eventData);
    }

    /**
     * Suggest a time to the user until the user agrees with the time
     * @param task the task to be scheduled to event
     * @return whether the task is successfully scheduled to event
     */
    @Override
    public boolean suggestTimeToUser(TaskInfo task) {
        List<LocalDateTime> unwantedTimes = new ArrayList<>();

        String response;
        LocalDateTime suggestedTime;
        Scanner scanner = new Scanner(System.in);

        do {
            suggestedTime = taskToEvent.getAvailableTime(
                    task, eventScheduler, unwantedTimes
            );
            System.out.println("Suggested time: " + suggestedTime);
            System.out.print("Type 'y' for yes, anything else for no: ");
            response = scanner.nextLine();
            // will be effective the next time the loop iterates
            unwantedTimes.add(suggestedTime);
        } while (!"y".equals(response));

        return eventController.createEvent(task.getName(), suggestedTime, task.getDuration());
    }

    /**
     * Check whether the time suggested by the user is available
     * @param task the task to be scheduled to event
     * @param userSuggestedTime the time suggested by the user
     * @return whether the task is successfully scheduled to event
     */
    @Override
    public boolean checkUserSuggestedTime(TaskInfo task, LocalDateTime userSuggestedTime) {
        return taskToEvent.checkTimeAvailability(task, eventScheduler, userSuggestedTime);
    }
}
