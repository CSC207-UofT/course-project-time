package services.event_creation;

import data_gateway.event.EventReader;
import services.event_presentation.EventInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInfoFromReader that = (EventInfoFromReader) o;
        return Objects.equals(eventReader, that.eventReader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventReader);
    }
}
