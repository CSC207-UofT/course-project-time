package services.notification;

import data_gateway.notification.NotificationManager;
import data_gateway.notification.NotificationReader;
import entity.Notification;
import services.notification_sending.NotificationPresenter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationTracker implements Runnable {
    private final List<NotificationPresenter> observers;
    private NotificationManager notificationManager;
    private NotificationReader upcomingNotification;

    public NotificationTracker(List<NotificationPresenter> observers,
                               NotificationManager notificationManager) {
        this.observers = observers;
        this.notificationManager = notificationManager;
        this.upcomingNotification = null;
    }

    /**
     * Delete a notification in notificationList with
     * associatedId being id and notificationTime being time
     * @param id the associatedId of the target notification
     * @param time the notification time of the target notification
     */
    public void deleteNotification(int id, LocalDateTime time) {
        notificationList.removeIf(notification ->
                id == notification.getAssociatedId() && time == notification.getNotificationDateTime());
    }

    /**
     * Delete all notifications in notificationList with associatedId being id
     * @param id the associated id that decide which notifications to delete
     */
    public void deleteNotifications(int id) {
        notificationList.removeIf(notification -> id == notification.getAssociatedId());
    }

    /***
     * Insert a notification to notificationList, based on the notification time.
     * @param newNotification the notification that will be added
     */
    public void addNotification(Notification newNotification) {
        LocalDateTime newNotificationTime = newNotification.getNotificationDateTime();
        for (int i = 0; i < notificationList.size(); i++) {
            if (newNotificationTime.isBefore(notificationList.get(i).getNotificationDateTime())) {
                notificationList.add(i, newNotification);
                return ;
            }
        }
        // either <notifications> is empty or it has been checked to the end
        notificationList.add(newNotification);
    }

    /***
     * Look up the notification with the associated id in the notificationList.
     * If the notification exists, return the notification time in advance of this notification.
     * If the notification does not exist, return null.
     * @param associatedId the associated id of the notification
     * @return notification time in advance if the notification exists;
     *         null if the notification does not exists.
     */
    public Duration getNotificationTimeInAdvance(long associatedId) {
        for (Notification notification : notificationList) {
            if (notification.getAssociatedId() == associatedId) {
                return notification.getNotificationTimeInAdvance();
            }
        }
        return null;
    }

    /**
     * Keep tracking the earliest notification.
     * If it's time to send the notification,
     * update all observers in observerList with the notification,
     * and then remove this notification from notificationList
     */
    @Override
    public void run() {
        // TODO: rerun the run() when notificationList is no longer empty
        while (true) {
            // remove overdue notifications
            if (upcomingNotification.getNotificationDateTime().isAfter(LocalDateTime.now())) {
                notificationList.remove(0);
            } else if (notificationList.get(0).getNotificationDateTime().equals(LocalDateTime.now())) {
                updateObservers(notificationList.get(0).getMessage());
                notificationList.remove(0);
            }
        }
    }

    /***
     * Helper method used by <run> method to update all observers with the new message to send
     * @param message the message to be sent by observers
     */
    private void updateObservers(String message) {
        for (NotificationPresenter observer : observers) {
            observer.presentNotification(message);
        }
    }
}
