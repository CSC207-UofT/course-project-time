package services.eventcreation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public interface EventWithNotificationModel extends CalendarEventModel{
    LocalDate getEventDate();
    LocalTime getEventStartTime();
    LocalTime getEventEndTime();
    Duration getNotificationTimeInAdvance();

}
