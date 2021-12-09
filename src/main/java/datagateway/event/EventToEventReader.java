package datagateway.event;

import entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class EventToEventReader implements EventReader{
    private final Event event;
    private final String name;
    private final boolean completed;

    public EventToEventReader(Event event, String name, boolean completed){
        this.event = event;
        this.name = name;
        this.completed = completed;
    }

    @Override
    public long getId() {
        return this.event.getId();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalTime getStartTime() {
        return event.getStartTime();
    }

    @Override
    public LocalTime getEndTime() {
        return event.getEndTime();
    }

    @Override
    public Set<String> getTags() {
        return event.getTags();
    }

    @Override
    public Set<LocalDate> getDates() {
        return event.getDates();
    }

    @Override
    public boolean getCompleted() {
        return completed;
    }
}
