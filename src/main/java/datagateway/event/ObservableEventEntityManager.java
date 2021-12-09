package datagateway.event;

import datagateway.Observer;
import entity.dates.DateStrategy;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class ObservableEventEntityManager implements ObservableEventRepository {

    private final CalendarManager calendarManager;
    private final List<Observer<EventReader>> onCreationObservers = new ArrayList<>();
    private final List<Observer<EventReader>> onUpdateObservers = new ArrayList<>();
    private final List<Observer<EventReader>> onDeleteObservers = new ArrayList<>();

    public ObservableEventEntityManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public void addCreationObserver(Observer<EventReader> observer) {
        onCreationObservers.add(observer);
    }

    @Override
    public void addUpdateObserver(Observer<EventReader> observer) {
        onUpdateObservers.add(observer);
    }

    @Override
    public void addDeleteObservers(Observer<EventReader> observer) {
        onDeleteObservers.add(observer);
    }

    private void notifyCreationObservers(EventReader er) {
        onCreationObservers.forEach(o -> o.notifyObserver(er));
    }

    private void notifyUpdateObservers(EventReader er) {
        onUpdateObservers.forEach(o -> o.notifyObserver(er));
    }

    private void notifyDeleteObservers(EventReader er) {
        onDeleteObservers.forEach(o -> o.notifyObserver(er));
    }

    @Override
    public long addEvent(String eventName, DateStrategy strategy, Duration duration, Set<String> tags) {
        long newEventId = calendarManager.addEvent(eventName, strategy, duration, tags);
        EventReader newEvent = getById(newEventId);
        notifyCreationObservers(newEvent);
        return newEventId;
    }

    @Override
    public long addEvent(long taskId, DateStrategy dateStrategy, Set<String> tags) {
        long newEventId = calendarManager.addEvent(taskId, dateStrategy, tags);
        EventReader newEvent = getById(newEventId);
        notifyCreationObservers(newEvent);
        return newEventId;
    }

    @Override
    public void deleteEvent(long eventId) {
        EventReader deletedEvent = getById(eventId);
        calendarManager.deleteEvent(eventId);
        notifyDeleteObservers(deletedEvent);
    }

    @Override
    public void markEventAsCompleted(long eventId) {
        calendarManager.markEventAsCompleted(eventId);
        EventReader updatedEvent = getById(eventId);
        notifyUpdateObservers(updatedEvent);
    }

    @Override
    public List<EventReader> getAllEvents() {
        return calendarManager.getAllEvents();
    }

    @Override
    public void updateName(long id, String newName) {
        calendarManager.updateName(id, newName);
        EventReader updatedEvent = getById(id);
        notifyUpdateObservers(updatedEvent);
    }

    @Override
    public void updateDateStrategy(long id, DateStrategy strategy) {
        calendarManager.updateDateStrategy(id, strategy);
        EventReader updatedEvent = getById(id);
        notifyUpdateObservers(updatedEvent);
    }

    @Override
    public void updateDuration(long id, Duration duration) {
        calendarManager.updateDuration(id, duration);
        EventReader updatedEvent = getById(id);
        notifyUpdateObservers(updatedEvent);
    }

    @Override
    public void addTag(long id, String tag) {
        calendarManager.addTag(id, tag);
        EventReader updatedEvent = getById(id);
        notifyUpdateObservers(updatedEvent);
    }

    @Override
    public void removeTag(long id, String tag) {
        calendarManager.removeTag(id, tag);
        EventReader updatedEvent = getById(id);
        notifyUpdateObservers(updatedEvent);
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
        throw new NoSuchElementException("Event with id " + eventId + " not found");
    }
}
