package services.event_creation;

import java.time.Duration;

public interface CalendarEventCreationBoundary {

    long addEvent(CalendarEventModel eventData, Duration notificationTimeInAdvance);

    void markEventAsCompleted(long eventId);
}
