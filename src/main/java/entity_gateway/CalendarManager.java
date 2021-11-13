package main.java.entity_gateway;

import main.java.use_case.CalendarEventModel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface CalendarManager {

    boolean addEvent(CalendarEventModel eventData);

    List<EventReader> getAllEvents();

    boolean update(String eventName, LocalDateTime startTime, LocalDateTime endTime);

}
