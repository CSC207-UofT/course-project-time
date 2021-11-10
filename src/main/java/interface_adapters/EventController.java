package main.java.interface_adapters;


import main.java.entity.Event;
import main.java.entity_gateway.EventEntityManager;
import main.java.use_case.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

public class EventController {

    protected final CalendarEventCreationBoundary eventAdder;
    protected final EventScheduler eventScheduler;
    protected final EventGetter eventGetter;
    protected final EventSaver eventSaver;

    public EventController(CalendarEventCreationBoundary eventAdder, EventScheduler eventScheduler, EventGetter eventGetter, EventSaver eventSaver) {
        this.eventAdder = eventAdder;
        this.eventScheduler = eventScheduler;
        this.eventGetter = eventGetter;
        this.eventSaver = eventSaver;
    }

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public List<HashMap<String, String>> getEvents() {
        return eventGetter.getEvents();
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param startDateTime start time including date
     * @param duration duration of event
     * @return whether the creation succeeded
     */
    public boolean createEvent(String eventName, LocalDateTime startDateTime, Duration duration) {
        // todo use exceptions to ensure that duration won't last until the next day
        LocalTime endTime = startDateTime.plus(duration.getSeconds(), ChronoUnit.SECONDS).toLocalTime();
        return createEvent(eventName, startDateTime.toLocalTime(), endTime, new HashSet<>(), startDateTime.toLocalDate());
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param startTime start time of event
     * @param endTime end time of event
     * @param tags relevant tags of event
     * @param dates date of which this event occurs
     * @return whether the event has been created successfully
     */
    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate dates) {

        if(eventScheduler.isAvailable(startTime, Duration.between(startTime, endTime), dates)) {
            return eventAdder.addEvent(new CalendarEventData(eventName,
                                        LocalDateTime.of(dates, startTime),
                                        LocalDateTime.of(dates, endTime),
                                        tags, dates));

        }
        return false;
    }

    public void saveEvents(String filename) throws IOException {
        this.eventSaver.SaveEventData(filename);
    }
}
