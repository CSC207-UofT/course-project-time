package services.notification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface NotificationFormatter {
    String formatTaskNotificationMessage(String taskName, LocalDateTime deadline);
    String formatEventNotificationMessage(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime);
}
