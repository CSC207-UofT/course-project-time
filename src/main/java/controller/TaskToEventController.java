package main.java.controller;

import main.java.entity.Task;
import main.java.use_case.TaskToEventAuto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskToEventController implements TaskToEventAutoController, TaskToEventManualController {
    private final TaskToEventAuto taskToEventAuto = new TaskToEventAuto();
//    private final TaskToEventManual taskToEventManual = new TaskToEventManual();

    protected final EventController eventController;

    public TaskToEventController(EventController eventController) {
        this.eventController = eventController;
    }

    /**
     * Suggest a time to the user until the user is agrees with the time
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
            suggestedTime = taskToEventAuto.getAvailableTime(
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

    @Override
    public LocalDateTime getUserSuggestedTime() {
        String format = "yyyy-MM-dd HH:mm";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input time for task in (" + format + ") (24 hour time):");
        String response = scanner.next(); //TODO exception handling
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(response, formatter);
        scanner.close();
        return dateTime;
    }
}
