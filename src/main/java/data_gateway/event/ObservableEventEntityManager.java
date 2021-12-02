package data_gateway.event;

import data_gateway.ObservableProperty;
import data_gateway.PropertyObserver;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class ObservableEventEntityManager implements ObservableEventManager {

    private final CalendarManager calendarManager;
    private final ObservableProperty<EventReader, Long> newEventObservers;
    private final ObservableProperty<EventReader, Boolean> updateCompletionObservers;

    public ObservableEventEntityManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
        newEventObservers = new ObservableProperty<>();
        updateCompletionObservers = new ObservableProperty<>();
    }

    public void addOnCreationObserver(PropertyObserver<EventReader, Long> observer) {
        newEventObservers.addObserver(observer);
    }

    public void addOnCompletionUpdateObserver(PropertyObserver<EventReader, Boolean> observer) {
        updateCompletionObservers.addObserver(observer);
    }

    @Override
    public long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, HashSet<String> tags, LocalDate date) {
        long newEventId = calendarManager.addEvent(eventName, startTime, endTime, tags, date);
        EventReader newEvent = getById(newEventId);
        newEventObservers.notifyObservers(newEvent, newEventId);
        return newEventId;
    }

    @Override
    public void markEventAsCompleted(long eventId) {
        calendarManager.markEventAsCompleted(eventId);
        EventReader updatedEvent = getById(eventId);
        updateCompletionObservers.notifyObservers(updatedEvent, true);
    }

    @Override
    public List<EventReader> getAllEvents() {
        return calendarManager.getAllEvents();
    }

    @Override
    public void loadEvents(String filePath) throws IOException {
        calendarManager.loadEvents(filePath);
    }

    @Override
    public void saveEvents(String savePath) throws IOException {
        calendarManager.saveEvents(savePath);
    }

    private EventReader getById(long eventId) {
        for (EventReader event: getAllEvents()) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }
}
