package datagateway.event;

import datagateway.Observer;
import entity.dates.DateStrategy;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class ObservableEventEntityManager implements ObservableEventRepository {

    private final CalendarManager calendarManager;
    private final List<Observer<EventReader>> onCreationObservers = new ArrayList<>();
    private final List<Observer<EventReader>> onUpdateObservers = new ArrayList<>();

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

    private void notifyCreationObservers(EventReader er) {
        onCreationObservers.forEach(o -> o.notifyObserver(er));
    }

    private void notifyUpdateObservers(EventReader er) {
        onUpdateObservers.forEach(o -> o.notifyObserver(er));
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
    public void updateStartTime(long id, LocalTime newStartTime) {
        calendarManager.updateStartTime(id, newStartTime);
        EventReader updatedEvent = getById(id);
        notifyUpdateObservers(updatedEvent);
    }

    @Override
    public void updateEndTime(long id, LocalTime newEndTime) {
        calendarManager.updateEndTime(id, newEndTime);
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
