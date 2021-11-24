package services.notification_system;

import services.event_creation.CalendarEventModel;
import services.task_creation.TodoListTaskCreationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NotificationFormatter implements NotificationFormat{
    @Override
    public String formatNotificationMessage(CalendarEventModel eventModel) {
        return null;
    }

    @Override
    public String formatNotificationMessage(TodoListTaskCreationModel taskModel) {
        return null;
    }

    @Override
    public String formatTaskNotificationMessage(String taskName, LocalDateTime deadline) {
        return "You have an upcoming task due at" + deadline.toString();
    }

    @Override
    public String formatEventNotificationMessage(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return "You have an upcoming event " + eventName +
                " from " + startTime.toString() +
                " to " + endTime.toString() +
                " on " + date.toString();
    }
}
