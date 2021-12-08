package services.eventcreation;

import datagateway.event.EventReader;
import services.eventpresentation.EventInfo;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public LocalTime getStartTime() {
        return eventReader.getStartTime();
    }

    @Override
    public LocalTime getEndTime() {
        return eventReader.getEndTime();
    }

    @Override
    public Set<String> getTags() {
        return eventReader.getTags();
    }

    @Override
    public Set<LocalDate> getDates() {
        return eventReader.getDates();
    }

    @Override
    public boolean getCompleted() {
        return eventReader.getCompleted();
    }

}
