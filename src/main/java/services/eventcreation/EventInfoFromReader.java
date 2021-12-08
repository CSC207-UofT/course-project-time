package services.eventcreation;

import datagateway.event.EventReader;
import entity.dates.TimeFrame;
import services.eventpresentation.EventInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public class EventInfoFromReader implements EventInfo {

    private final EventReader eventReader;

    public EventInfoFromReader(EventReader eventReader) {
        this.eventReader = eventReader;
    }

    @Override
    public long getId() {
        return this.eventReader.getId();
    }

    @Override
    public String getName() {
        return eventReader.getName();
    }

    @Override
    public Duration getDuration() {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return eventReader.getTags();
    }

    @Override
    public Set<TimeFrame> getDatesBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return eventReader.getDatesBetween(startTime, endTime);
    }

    @Override
    public String getWhen() {
        return eventReader.getWhen();
    }

    @Override
    public boolean getCompleted() {
        return eventReader.getCompleted();
    }

}
