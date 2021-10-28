package main.java.use_case;

import main.java.entity_gateway.EventReader;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class EventInfoFromReader implements EventInfo {

    private final EventReader eventReader;

    public EventInfoFromReader(EventReader eventReader) {
        this.eventReader = eventReader;
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
}
