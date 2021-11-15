package main.java.services.event_presentation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface EventInfo {
    Long getId();

    String getName();

    LocalTime getStartTime();

    LocalTime getEndTime();

    Set<String> getTags();

    Set<LocalDate> getDates();
}
