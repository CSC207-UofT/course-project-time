package services.notification_system;

import data_gateway.EventReader;
import data_gateway.TaskReader;
import entity.Notification;
import services.event_creation.CalendarEventModel;
import services.task_creation.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class NotificationAdder {
    private final NotificationTracker notificationTracker;
    private final NotificationFormat notificationFormat;

    public NotificationAdder(NotificationTracker notificationTracker, NotificationFormat notificationFormat) {
        this.notificationTracker = notificationTracker;
        this.notificationFormat = notificationFormat;
    }

    public void createNotification(CalendarEventModel eventData, long eventId) {
        // TODO: add duration in advance to CalendarEventModel and other related classes
        // will need to use for loop
        LocalDateTime notificationTime = null;
        String message = notificationFormat.formatNotificationMessage(eventData);
        Notification notification = new Notification(eventId, notificationTime, message);
        notificationTracker.addNotification(notification);

    }

    public void loadEventNotifications(List<EventReader> eventReaders) {
        for (EventReader eventReader : eventReaders) {
            long eventId = eventReader.getId();
            String eventName = eventReader.getName();
            // TODO: add time in advance to related classes
            Duration timeInAdvance = null;
            LocalTime startTime = eventReader.getStartTime();
            LocalTime endTime = eventReader.getEndTime();
            Set<LocalDate> dates = eventReader.getDates();
            for (LocalDate date : dates) {
                LocalDateTime startDateTime = date.atTime(startTime);
                LocalDateTime notificationTime = startDateTime.minus(timeInAdvance);
                String message = notificationFormat.formatEventNotificationMessage(eventName, date, startTime, endTime);
                Notification notification = new Notification(eventId, notificationTime, message);
                this.notificationTracker.addNotification(notification);
            }
        }
    }

    public void createNotification(TodoListTaskCreationModel taskData, long taskId) {
        // TODO: add duration in advance to TodoListTaskCreationModel and other related classes
        LocalDateTime notificationTime = taskData.getDeadline();
        String message = notificationFormat.formatNotificationMessage(taskData);
        Notification notification = new Notification(taskId, notificationTime, message);
        notificationTracker.addNotification(notification);
    }

    public void loadTaskNotifications(List<TaskReader> taskReaders) {
        for (TaskReader taskReader : taskReaders) {
            long taskId = taskReader.getId();
            String taskName = taskReader.getName();
            // TODO: add time in advance to related classes
            Duration timeInAdvance = null;
            LocalDateTime deadline = taskReader.getDeadline();
            LocalDateTime notificationTime = deadline.minus(timeInAdvance);
            String message = notificationFormat.formatTaskNotificationMessage(taskName, deadline);
            Notification notification = new Notification(taskId, notificationTime, message);
            this.notificationTracker.addNotification(notification);
        }
    }
}
