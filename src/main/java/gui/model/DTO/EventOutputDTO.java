package gui.model.DTO;

import services.event_presentation.EventInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class EventOutputDTO implements EventInfo {
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public LocalTime getStartTime() {
        return null;
    }

    @Override
    public LocalTime getEndTime() {
        return null;
    }

    @Override
    public Set<String> getTags() {
        return null;
    }

    @Override
    public Set<LocalDate> getDates() {
        return null;
    }

    @Override
    public boolean getCompleted() {
        return false;
    }
}
