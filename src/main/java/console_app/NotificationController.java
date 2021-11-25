package console_app;

import data_gateway.EventReader;
import data_gateway.TaskReader;
import services.notification_system.NotificationAdder;
import services.notification_system.NotificationSettings;
import services.notification_system.NotificationSettingsManager;
import services.notification_system.NotificationTracker;

import java.util.List;
import java.util.Map;

public class NotificationController {
    private NotificationTracker notificationTracker;
    private NotificationAdder notificationAdder;
    private NotificationSettings notificationSettings;

    public NotificationController(NotificationTracker notificationTracker, NotificationAdder notificationAdder) {
        this.notificationTracker = notificationTracker;
        this.notificationAdder = notificationAdder;
        notificationSettings = new NotificationSettingsManager();
    }

    /***
     * Let notificationTracker start tracking the upcoming notifications
     */
    public void startTracking() {
        notificationTracker.run();
    }

    /***
     * update the notification settings and save the new settings to the database
     * @param newSettings the new notification settings
     */
    public void updateNotificationSettings(Map<String, Boolean> newSettings) {
        notificationSettings.saveNewSettings(newSettings);
    }

    /***
     * Load the event notifications and task notifications from the database
     * @param eventReaders a list of EvenReader that stores the information of events from the database
     * @param taskReaders a list of TaskReader that stores the information of tasks from the database
     */
    public void loadNotifications(List<EventReader> eventReaders, List<TaskReader> taskReaders) {
        notificationAdder.loadEventNotifications(eventReaders);
        notificationAdder.loadTaskNotifications(taskReaders);
    }
}
