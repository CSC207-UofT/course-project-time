package main.java.interface_adapters;

import main.java.entity_gateway.CalendarManager;
import main.java.use_case.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RescheduleController implements RescheduleAutoController{
    CalendarManager calendarManager;
    private final Reschedule reschedule = new Reschedule(null);
    private final EventInfo eventInfo;
    private EventScheduler eventScheduler;


    public RescheduleController(EventInfo event, EventScheduler eventScheduler){
        this.eventInfo = event;
        this.eventScheduler = eventScheduler;
    }

    @Override
    public boolean rescheduleTime(TaskInfo task) {
        //ask user for what time they would prefer from reschedule.getAvailableTime
        LocalDateTime startTime;
        LocalDateTime endTime;
        List<LocalDateTime> timesToIgnore = new ArrayList<>();
        String response;
        Scanner scanner = new Scanner(System.in);

        do {
            startTime = reschedule.getAvailableTime(task, eventScheduler, timesToIgnore);
            System.out.println("Would you like to reschedule to: " + startTime + "?");
            System.out.print("Type 'y' for yes, anything else for no: ");
            response = scanner.nextLine();
            timesToIgnore.add(startTime);
        } while (!"y".equals(response));
        endTime = startTime.plus(task.getDuration());
        Long eventId = eventInfo.getId();
        return reschedule.update(eventId, startTime, endTime);
    }

    public boolean requestedTime(TaskInfo taskInfo, LocalDateTime userRequestedTime){
        Long eventId = eventInfo.getId();
        if(reschedule.checkRequestedTime(taskInfo, eventScheduler, userRequestedTime)){
            LocalDateTime userEndTime = userRequestedTime.plus(taskInfo.getDuration());
            return reschedule.update(eventId, userRequestedTime, userEndTime);
        }
        return false;
    }

}