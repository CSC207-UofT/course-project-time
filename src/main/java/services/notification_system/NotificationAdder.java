package services.notification_system;

import data_gateway.EventReader;
import data_gateway.TaskReader;
import entity.Calendar;
import services.event_creation.CalendarEventModel;
import services.task_creation.TodoListTaskCreationModel;

import java.util.List;

public class NotificationAdder {
    private final NotificationTracker notificationTracker;
    private NotificationFormat notificationFormat;

    public NotificationAdder(NotificationTracker notificationTracker, NotificationFormat notificationFormat) {
        this.notificationTracker = notificationTracker;
        this.notificationFormat = notificationFormat;
    }

    public boolean createNotification(CalendarEventModel eventModel) {

    }

    public boolean loadEventNotifications(List<EventReader> eventReaders) {

    }

    public boolean createNotification(TodoListTaskCreationModel taskModel) {

    }

    public boolean loadTaskNotifications(List<TaskReader> taskReaders) {

    }
}
