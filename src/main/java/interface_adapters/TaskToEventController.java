package main.java.interface_adapters;

import main.java.entity.Task;
import main.java.use_case.TaskToEvent;
import main.java.use_case.EventFromTaskCreatorBoundary;
import main.java.use_case.EventFromTaskId;
import main.java.use_case.EventFromTaskModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskToEventController implements TaskToEventAutoController, TaskToEventManualController {

    private final TaskToEvent taskToEvent = new TaskToEvent();
    private final EventFromTaskCreatorBoundary eventFromTaskCreatorBoundary;

    protected final EventController eventController;

    public TaskToEventController(EventController eventController, EventFromTaskCreatorBoundary eventFromTaskBoundary) {
        this.eventFromTaskCreatorBoundary = eventFromTaskBoundary;
        this.eventController = eventController;
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
    public boolean suggestTimeToUser(Task task) {
        List<LocalDateTime> unwantedTimes = new ArrayList<>();

        String response;
        LocalDateTime suggestedTime;
        Scanner scanner = new Scanner(System.in);

        do {
            suggestedTime = taskToEvent.getAvailableTime(
                    task, eventController.eventScheduler, unwantedTimes
            );
            System.out.println("Suggested time: " + suggestedTime);
            System.out.print("Type 'y' for yes, anything else for no: ");
            response = scanner.nextLine();
            // will be effective the next time the loop iterates
            unwantedTimes.add(suggestedTime);
        } while (!"y".equals(response));

        return eventController.createEvent(task.getTaskName(), suggestedTime, task.getTimeNeeded());
    }

    /**
     * Check whether the time suggested by the user is available
     * @param task the task to be scheduled to event
     * @param userSuggestedTime the time suggested by the user
     * @return whether the task is successfully scheduled to event
     */
    @Override
    public boolean checkUserSuggestedTime(Task task, LocalDateTime userSuggestedTime) {
        return taskToEvent.checkTimeAvailability(task, eventController.eventScheduler, userSuggestedTime);
    }
}
