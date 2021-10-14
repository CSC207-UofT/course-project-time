package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskToEventManual implements TaskToEvent {

    /**
     *
     * @param task      the task that needs to be converted into an event
     * @param calendar  the calendar that contains the user's available times
     * @return an Event scheduled at a user suggested time
     */
    @Override
    public Event createEventFromTask(Task task, Calendar calendar, EventScheduler eventScheduler) {
        LocalDateTime userSuggestedTime = getUserSuggestedTime();
        boolean validTime = eventScheduler.checkAvailability(userSuggestedTime, calendar);
        while (!validTime) {
            userSuggestedTime = getUserSuggestedTime();
            validTime = eventScheduler.checkAvailability(userSuggestedTime, calendar);
        }
        return new Event(task, userSuggestedTime, userSuggestedTime.toLocalTime().plus(task.getTimeNeeded()));
    }

    /**
     * get user to input a time to schedule the event
     * @return the time suggested by the user as a LocalDateTime instance
     */
    private LocalDateTime getUserSuggestedTime() {
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