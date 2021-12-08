package datagateway.event;

import datagateway.strategy.SessionDateStrategy;
import entity.Event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public class EventToEventReader implements EventReader{
    private final Event event;
    private final SessionDateStrategy sessionDateStrategy;

    public EventToEventReader(Event event, SessionDateStrategy sessionDateStrategy ){
        this.event = event;
        this.sessionDateStrategy = sessionDateStrategy;
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
    public Duration getDuration() {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return event.getTags();
    }

    @Override
    public Set<LocalDateTime> getDates() {
        return event.getDates();
    }

    @Override
    public boolean getCompleted() {
        return event.getCompleted();
    }
}
