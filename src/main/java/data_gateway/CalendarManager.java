package main.java.data_gateway;

import main.java.services.event_creation.CalendarEventModel;

import java.util.List;

public interface CalendarManager {

    boolean addEvent(CalendarEventModel eventData);

    List<EventReader> getAllEvents();

}
