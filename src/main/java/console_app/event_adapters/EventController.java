package console_app.event_adapters;


import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.EventGetter;
import services.event_presentation.EventInfo;
import services.update_entities.EventUpdater;
import services.update_entities.UpdateEventBoundary;
import services.strategy_building.DatesForm;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EventController {

    private final CalendarEventCreationBoundary eventAdder;
    private final EventGetter eventGetter;
    private final EventSaver eventSaver;
    private final UpdateEventBoundary eventUpdater;


    public EventController(CalendarEventCreationBoundary eventAdder, EventGetter eventGetter, EventSaver eventSaver,
                           EventUpdater eventUpdater) {
        this.eventAdder = eventAdder;
        this.eventGetter = eventGetter;
        this.eventSaver = eventSaver;
        this.eventUpdater = eventUpdater;
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

    public void updateName(long id, String newName){eventUpdater.updateName(id, newName);}

    public void updateStartTime(long id, LocalTime newStartTime){eventUpdater.updateStartTime(id, newStartTime);}

    public void updateEndTime(long id, LocalTime newEndTime){eventUpdater.updateEndTime(id, newEndTime);}

    public void addTag(long id, String tag){eventUpdater.addTag(id, tag);}

    public void removeTag(long id, String tag){eventUpdater.removeTag(id, tag);}

    public void markEventAsCompleted(long id){eventUpdater.markEventAsCompleted(id);}

    public EventInfo getEventByName(String name) {
        return eventGetter.getEventByName(name);
    }

    public List<HashMap<String, String>> getEvents() {
        return eventGetter.getEvents();
    }
}
