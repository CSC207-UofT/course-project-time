package services.eventcreation;

import java.time.LocalDate;
import java.time.LocalTime;

public interface EventNotificationFormatter {
    String formatMessage(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime);
}
