package main.java.entity_gateway;

import main.java.use_case.CalendarEventModel;

import java.util.List;

public interface CalendarManager {

    boolean addEvent(CalendarEventModel eventData);

    List<EventReader> getAllEvents();

}
