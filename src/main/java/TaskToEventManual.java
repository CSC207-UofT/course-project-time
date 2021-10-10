package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TaskToEventManual implements TaskToEvent {

    /**
     *
     * @param task      the task that needs to be converted into an event
     * @param schedule  the schedule that the event will be added into
     * @return an Event scheduled at a user suggested time
     */
    @Override
    public Event createEventFromTask(Task task, Schedule schedule) {
        LocalDateTime userSuggestedTime = getUserSuggestedTime();
        boolean validTime = schedule.checkAvailability(userSuggestedTime);
        while (!validTime) {
            userSuggestedTime = getUserSuggestedTime();
            validTime = schedule.checkAvailability(userSuggestedTime);
        }
        return Event(task, userSuggestedTime);
    }

    /**
     * get user to input a time to schedule the event
     * @return the time suggested by the user as a LocalDateTime instance
     */
    private LocalDateTime getUserSuggestedTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input time for task in (yyyy-MM-dd HH:mm) (24 hour time):");
        String response = scanner.next(); //TODO exception handling
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(response, formatter);
        scanner.close();
        return dateTime;
    }
}
