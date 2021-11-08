package main.java.entity_gateway;

import main.java.use_case.CalendarEventCreationDTO;

import java.util.List;

public interface CalendarManager {

    boolean addEvent(CalendarEventCreationDTO eventData);

    List<EventReader> getAllEvents();

}
