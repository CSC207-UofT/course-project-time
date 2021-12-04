package services.notification;

import services.event_creation.CalendarEventModel;
import services.task_creation.TodoListTaskCreationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface NotificationFormat {
    String formatNotificationMessage(CalendarEventModel eventModel);
    String formatNotificationMessage(TodoListTaskCreationModel taskModel);
    String formatTaskNotificationMessage(String taskName, LocalDateTime deadline);
    String formatEventNotificationMessage(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime);
}
