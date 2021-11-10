/*
    Used to access information about an event, note that this is separate from CalendarEventModel,
    CalendarEventModel represents the data of an event scheduled in a Calendar, EventReader is used to read
    the data of an event, which may become multiple, regular events when scheduled.
 */

package main.java.entity_gateway;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface EventReader {
    String getName();

    LocalTime getStartTime();

    LocalTime getEndTime();

    Set<String> getTags();

    Set<LocalDate> getDates();
}
