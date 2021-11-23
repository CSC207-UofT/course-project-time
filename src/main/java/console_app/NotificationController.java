package console_app;

import data_gateway.EventReader;
import data_gateway.TaskReader;
import services.notification_system.NotificationAdder;
import services.notification_system.NotificationTracker;

import java.util.List;

public class NotificationController {
    private NotificationTracker notificationTracker;
    private NotificationAdder notificationAdder;

    public NotificationController(NotificationTracker notificationTracker, NotificationAdder notificationAdder) {
        this.notificationTracker = notificationTracker;
        this.notificationAdder = notificationAdder;
    }

    public void startTracking() {

    }

    public boolean updateNotificationSettings() {

    }

    public boolean loadEventNotifications(List<EventReader> eventReaders) {

    }

    public boolean loadTaskNotifications(List<TaskReader> taskReaders) {

    }
}
