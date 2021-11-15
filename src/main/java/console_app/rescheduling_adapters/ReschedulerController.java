package main.java.console_app.rescheduling_adapters;

import main.java.data_gateway.CalendarManager;
import main.java.services.event_from_task_creation.EventScheduler;
import main.java.services.event_presentation.EventInfo;
import main.java.services.rescheduling.Rescheduler;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReschedulerController implements ReschedulerAutoController {
    private final Rescheduler rescheduler;
    private final EventScheduler eventScheduler;


    public ReschedulerController(CalendarManager calendarManager, EventScheduler eventScheduler){
        this.rescheduler = new Rescheduler(calendarManager);
        this.eventScheduler = eventScheduler;
    }

    /**
     * Suggest a new time to the user until the user agrees with the new time to reschedule event
     * @param event the event that needs to be rescheduled
     * @return whether the event was rescheduled successfully
     */
    @Override
    public boolean rescheduleTime(EventInfo event) {
        //ask user for what time they would prefer from reschedule.getAvailableTime
        LocalDateTime startTime;
        LocalDateTime endTime;
        List<LocalDateTime> timesToIgnore = new ArrayList<>();
        String response;
        Scanner scanner = new Scanner(System.in);

        do {
            startTime = rescheduler.getAvailableTime(event, eventScheduler, timesToIgnore);
            System.out.println("Would you like to reschedule to: " + startTime + "?");
            System.out.print("Type 'y' for yes, anything else for no: ");
            response = scanner.nextLine();
            timesToIgnore.add(startTime);
        } while (!"y".equals(response));
        endTime = startTime.plus(rescheduler.getDuration(event));
        Long eventId = event.getId();
        return rescheduler.update(eventId, startTime, endTime);
    }


    /**
     * Check whether the time suggested by the user is available
     * @param event the event that needs to be rescheduled
     * @param userRequestedTime the time suggested by the user
     * @return whether the event was rescheduled successfully
     */
    public boolean requestedTime(EventInfo event, LocalDateTime userRequestedTime){
        Long eventId = event.getId();
        if(rescheduler.checkRequestedTime(event, eventScheduler, userRequestedTime)){
            LocalDateTime userEndTime = userRequestedTime.plus(rescheduler.getDuration(event));
            return rescheduler.update(eventId, userRequestedTime, userEndTime);
        }
        return false;
    }

}