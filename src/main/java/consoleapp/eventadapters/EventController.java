package consoleapp.eventadapters;


import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventSaver;
import services.eventdeletion.EventDeletionBoundary;
import services.eventpresentation.CalendarEventDisplayBoundary;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.eventpresentation.EventInfo;
import services.updateentities.UpdateEventBoundary;
import services.strategybuilding.DatesForm;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EventController {

    private final CalendarEventCreationBoundary eventAdder;
    private final CalendarEventRequestBoundary eventGetter;
    private final CalendarEventDisplayBoundary eventOutputter;
    private final EventSaver eventSaver;
    private final UpdateEventBoundary eventUpdater;
    private final EventDeletionBoundary eventDeleter;


    public EventController(CalendarEventCreationBoundary eventAdder, CalendarEventRequestBoundary eventGetter,
                           CalendarEventDisplayBoundary eventOutputter, EventSaver eventSaver, UpdateEventBoundary eventUpdater,
                           EventDeletionBoundary eventDeleter) {
        this.eventAdder = eventAdder;
        this.eventGetter = eventGetter;
        this.eventOutputter = eventOutputter;
        this.eventSaver = eventSaver;
        this.eventUpdater = eventUpdater;
        this.eventDeleter = eventDeleter;
    }

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public void presentAllEvents() {
        eventOutputter.presentAllEvents();
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
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        List<EventInfo> eventInfos = eventGetter.getEvents();
        for (EventInfo info : eventInfos) {
            HashMap<String, String> infoMap = new HashMap<>();
            infoMap.put("name", info.getName());
            infoMap.put("start", info.getStartTime().toString());
            infoMap.put("end", info.getEndTime().toString());
            infoMap.put("tags", info.getTags().toString());
            infoMap.put("dates", info.getDates().toString());
            hashMapList.add(infoMap);
        }
        return hashMapList;
    }

    public void deleteEvent(long eventId) {
        eventDeleter.deleteEvent(eventId);
    }
}
