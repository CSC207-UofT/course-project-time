package consoleapp;

import datagateway.notification.NotificationManager;
import datagateway.notification.NotificationReader;
import datagateway.notification.SettingsManager;
import services.notification.NotificationAdder;
import services.notification.NotificationRemover;
import services.notification.NotificationTracker;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class NotificationController {

    private final NotificationManager notificationManager;
    private final NotificationTracker notificationTracker;
    private final NotificationAdder notificationAdder;
    private final NotificationRemover notificationRemover;
    private final SettingsManager settingsManager;

    public NotificationController(NotificationManager notificationManager, NotificationTracker notificationTracker,
                                  NotificationAdder notificationAdder,
                                  NotificationRemover notificationRemover, SettingsManager settingsManager) {
        this.notificationManager = notificationManager;
        this.notificationTracker = notificationTracker;
        this.notificationAdder = notificationAdder;
        this.notificationRemover = notificationRemover;
        this.settingsManager = settingsManager;
    }

    /**
     * run notificationTracker to track the upcoming notifications
     */
    public void startTracking() {
        notificationTracker.run();
    }

    /**
     * Update the notification settings
     * @param newSettings a map of settings with keys being "email" and "desktop"
     *                    and values being boolean
     */
    public void updateNotificationSettings(Map<String, Boolean> newSettings){
        settingsManager.setNotificationSettings(newSettings);
    }

    /**
     * return a list of NotificationReaders with associated id being associatedId
     * @param associatedId the associated id
     * @return a list of NotificationReaders
     */
    public List<NotificationReader> getNotificationTimeInAdvance(long associatedId) {
        return notificationManager.getNotificationsForAssociatedObject(associatedId);
    }

    /**
     * Deletes notifications with associatedId
     * This method is called when a task or event is deleted
     * @param associatedId the id of the associated object that the notification is for
     */
    public void deleteNotification(long associatedId) {
        notificationRemover.deleteNotification(associatedId);
    }

    /**
     * Deletes a notification
     * This method is called when a task notification or event notification is deleted
     * @param associatedId the id of the associated object that the notification is for
     * @param notificationTimeInAdvance the duration in advance that the notification is to be sent out
     */
    public void deleteNotification(long associatedId, Duration notificationTimeInAdvance) {
        notificationRemover.deleteNotification(associatedId, notificationTimeInAdvance);
    }

}
