package gui.model.DTO;

import services.event_creation.CalendarEventModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class EventCreationDTO implements CalendarEventModel {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public LocalDateTime getStartTime() {
        return null;
    }

    @Override
    public LocalDateTime getEndTime() {
        return null;
    }

    @Override
    public HashSet<String> getTags() {
        return null;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
}
