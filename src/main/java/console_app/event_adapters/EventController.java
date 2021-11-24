package console_app.event_adapters;


import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_presentation.EventGetter;
import services.event_presentation.EventInfo;
import services.strategy_building.DatesForm;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EventController {

    private final CalendarEventCreationBoundary eventAdder;
    private final EventGetter eventGetter;
    private final EventSaver eventSaver;

    public EventController(CalendarEventCreationBoundary eventAdder, EventGetter eventGetter, EventSaver eventSaver) {
        this.eventAdder = eventAdder;
        this.eventGetter = eventGetter;
        this.eventSaver = eventSaver;
    }

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public void presentAllEvents() {
        eventGetter.presentAllEvents();
    }

    /**
     * {@link #createEvent(String, Duration, DatesForm, HashSet)}
     */
    public void createEvent(String eventName, Duration duration, DatesForm form) {
        createEvent(eventName, duration, form, new HashSet<>());
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param duration how long this event will go on for
     * @param form the StrategyBuilderDirector form used to generate the dates strategy
     * @param tags relevant tags of event
     */
    public void createEvent(String eventName, Duration duration, DatesForm form, HashSet<String> tags) {

        eventAdder.addEvent(new CalendarEventData(eventName, duration, form, tags));
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
