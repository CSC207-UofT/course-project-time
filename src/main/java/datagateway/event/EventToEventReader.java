package datagateway.event;

import entity.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class EventToEventReader implements EventReader{
    private final Event event;

    public EventToEventReader(Event event){
        this.event = event;
    }

    @Override
    public long getId() {
        return this.event.getId();
    }

    @Override
    public String getName() {
        return event.getEventName();
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
        return event.getCompleted();
    }
}
