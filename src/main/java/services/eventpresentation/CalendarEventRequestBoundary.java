package services.eventpresentation;

import java.util.List;

public interface CalendarEventRequestBoundary {
    List<EventInfo> getEvents();
    EventInfo getEventByName(String name);
}
