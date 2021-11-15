package main.java.console_app.event_adapters;


import main.java.services.Snowflake;
import main.java.services.event_creation.CalendarEventCreationBoundary;
import main.java.services.event_creation.EventSaver;
import main.java.services.event_from_task_creation.EventScheduler;
import main.java.services.event_presentation.EventGetter;
import main.java.services.event_presentation.EventInfo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EventController {

    private final CalendarEventCreationBoundary eventAdder;
    private final EventScheduler eventScheduler;
    private final EventGetter eventGetter;
    private final Snowflake snowflake;
    private final EventSaver eventSaver;

    public EventController(CalendarEventCreationBoundary eventAdder, EventScheduler eventScheduler,
                           EventGetter eventGetter, EventSaver eventSaver, Snowflake snowflake) {
        this.eventAdder = eventAdder;
        this.eventScheduler = eventScheduler;
        this.eventGetter = eventGetter;
        this.snowflake = snowflake;
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
        return createEvent(eventName, startDateTime.toLocalTime(), endTime, new HashSet<String>(), startDateTime.toLocalDate());
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
        this.eventSaver.saveEventData(filename);
    }

    public EventInfo getEventByName(String name) {
        return eventGetter.getEventByName(name);
    }

    public boolean completeEvent(String eventName) {
        return eventAdder.completeEvent(eventName);
    }
}
