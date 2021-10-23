package main.old.controller;

import main.old.entity.Task;
import main.old.use_case.TaskToEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskToEventController implements TaskToEventAutoController, TaskToEventManualController {
    private final TaskToEvent taskToEvent = new TaskToEvent();

    protected final EventController eventController;

    public TaskToEventController(EventController eventController) {
        this.eventController = eventController;
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
                    task, eventController.calendarData.getCalendar(), eventController.eventScheduler, unwantedTimes
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
        return taskToEvent.checkTimeAvailability(task, eventController.calendarData, eventController.eventScheduler, userSuggestedTime);
    }
}
