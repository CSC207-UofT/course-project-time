package main.java;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskToEventAuto implements TaskToEvent {
    /**
     *
     * @param task      the task that needs to be converted into an event
     * @param schedule  the schedule that the event will be added into
     * @return an Event scheduled at a suggested time,
     *          depending on the available times
     */
    @Override
    public Event createEventFromTask(Task task, Schedule schedule) {
        // if we have different schedules then the available time we get from for example the school schedule might be scheduled time in the family schedule
        // potentially add a tag to events that will store the "schedule"

        List<LocalDateTime> suggestedTimes = new ArrayList<>();
        LocalDateTime availableTime = schedule.getAvailableTime(suggestedTimes, task.getDurationNeeded());
        boolean scheduled = suggestTimeToUser(availableTime);

        suggestedTimes.add(availableTime);

        while (!scheduled) {
            availableTime = schedule.getAvailableTime(suggestedTimes, task.getDurationNeeded());
            scheduled = suggestTimeToUser(availableTime);
            availableTime.add(availableTime);
        }

        Event event = new Event(Task, availableTime);
        return event;
    }

    /**
     * prints the time to the user and receives user input
     * @param time an available time for the event
     * @return  a boolean indicating if the user agrees with the suggested time
     */
    private boolean suggestTimeToUser(LocalDateTime time) {
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

