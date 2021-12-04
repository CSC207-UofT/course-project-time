package console_app;

import data_gateway.event.EventReader;
import data_gateway.notification.SettingsManager;
import data_gateway.task.TaskReader;
import services.notification.NotificationAdder;
import services.notification.NotificationTracker;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class NotificationController {
    private final NotificationTracker notificationTracker;
    private final NotificationAdder notificationAdder;
    private final SettingsManager notificationSettings;

    public NotificationController(NotificationTracker notificationTracker,
                                  NotificationAdder notificationAdder, SettingsManager notificationSettings) {
        this.notificationTracker = notificationTracker;
        this.notificationAdder = notificationAdder;
        this.notificationSettings = notificationSettings;
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
        notificationSettings.setNotificationSettings(newSettings);
    }

    /***
     * Look up the notification with the associated id.
     * If the notification exists, return the notification time in advance of this notification.
     * If the notification does not exist, return null.
     * @param associatedId the associated id of the notification
     * @return notification time in advance if the notification exists;
     *         null if the notification does not exists.
     */
    public Duration getNotificationTimeInAdvance(long associatedId) {
        return notificationTracker.getNotificationTimeInAdvance(associatedId);
    }
}