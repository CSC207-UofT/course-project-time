package main.java.services.event_creation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public interface CalendarEventModel {
    public String getName();
    public LocalDateTime getStartTime();
    public LocalDateTime getEndTime();
    public HashSet<String> getTags();
    public LocalDate getDate();

}
