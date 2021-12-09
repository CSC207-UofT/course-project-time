package datagateway.event;

import datagateway.task.TaskReader;
import entity.Event;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class EventToEventReader implements EventReader{
    private final Event event;
    private final TaskReader task;

    public EventToEventReader(Event event, TaskReader associatedTask){
        this.event = event;
        this.task = associatedTask;
    }

    @Override
    public long getId() {
        return this.event.getId();
    }

    @Override
    public String getName() {
        return task.getName();
    }

    @Override
    public Duration getDuration() {
        return task.getDuration();
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
        return task.getCompleted();
    }
}
