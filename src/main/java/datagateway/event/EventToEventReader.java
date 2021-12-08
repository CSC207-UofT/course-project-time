package datagateway.event;

import entity.Event;
import entity.dates.DateStrategy;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    public Duration getDuration() {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return event.getTags();
    }

    @Override
    public Set<TimeFrame> getDatesBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return new HashSet<>(event.getDateStrategy().datesBetween(startTime, endTime, getDuration()));
    }

    @Override
    public String getWhen() {
        return event.getDateStrategy().toString();
    }

    @Override
    public boolean getCompleted() {
        return completed;
    }
}
