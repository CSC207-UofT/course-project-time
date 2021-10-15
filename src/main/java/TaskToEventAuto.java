package main.java;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskToEventAuto implements TaskToEvent {
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

        Event event = new Event(task, availableTime, availableTime.toLocalTime().plus(task.getTimeNeeded()));
        return event;
    }

    /**
     * prints the time to the user and receives user input
     * @param time an available time for the event
     * @return  a boolean indicating if the user agrees with the suggested time
     */
    private boolean confirmTimeWithUser(LocalDateTime time) {
        boolean scheduled;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Suggested time: " + time);
        System.out.println("Type 0 for yes, 1 for no");
        int response = scanner.nextInt(); //TODO exception handling
        System.out.println("Type 0 for yes, 1 for no");
        response = scanner.nextInt();
        scheduled = response == 0;
        scanner.close();
        return scheduled;
    }
}

