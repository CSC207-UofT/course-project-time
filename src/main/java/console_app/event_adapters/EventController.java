package console_app.event_adapters;


import console_app.NotificationController;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.EventGetter;
import services.event_presentation.EventInfo;

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
    private final CalendarAnalyzer eventScheduler;
    private final EventGetter eventGetter;
    private final EventSaver eventSaver;
    private final NotificationController notificationController;

    public EventController(CalendarEventCreationBoundary eventAdder, CalendarAnalyzer eventScheduler,
                           EventGetter eventGetter, EventSaver eventSaver, NotificationController notificationController) {
        this.eventAdder = eventAdder;
        this.eventScheduler = eventScheduler;
        this.eventGetter = eventGetter;
        this.eventSaver = eventSaver;
        this.notificationController = notificationController;
    }

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public void presentAllEvents() {
        eventGetter.presentAllEvents();
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param startDateTime start time including date
     * @param duration duration of event
     */
    public void createEvent(String eventName, LocalDateTime startDateTime, Duration duration) {
        // todo use exceptions to ensure that duration won't last until the next day
        LocalTime endTime = startDateTime.plus(duration.getSeconds(), ChronoUnit.SECONDS).toLocalTime();
        createEvent(eventName, startDateTime.toLocalTime(), endTime, new HashSet<>(), startDateTime.toLocalDate());
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param startTime start time of event
     * @param endTime end time of event
     * @param tags relevant tags of event
     * @param date date of which this event occurs
     */
    public void createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                            HashSet<String> tags, LocalDate date) {

        if(eventScheduler.isAvailable(startTime, Duration.between(startTime, endTime), date)) {
            eventAdder.addEvent(new CalendarEventData(eventName,
                    LocalDateTime.of(date, startTime),
                    LocalDateTime.of(date, endTime),
                    tags));
        }
    }

    public void saveEvents(String filename) throws IOException {
        this.eventSaver.saveEventData(filename);
    }

    public EventInfo getEventByName(String name) {
        return eventGetter.getEventByName(name);
    }

    public void markEventAsCompleted(long eventId) {
        eventAdder.markEventAsCompleted(eventId);
    }

    public List<HashMap<String, String>> getEvents() {
        return eventGetter.getEvents();
    }
}
