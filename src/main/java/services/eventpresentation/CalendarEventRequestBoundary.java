package services.eventpresentation;

import java.util.HashMap;
import java.util.List;

public interface CalendarEventRequestBoundary {
    List<HashMap<String, String>> getEvents();
    EventInfo getEventByName(String name);
}
