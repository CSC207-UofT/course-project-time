package services.notification;

import datagateway.notification.NotificationManager;
import datagateway.notification.NotificationReader;
import services.notificationsending.NotificationPresenter;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationTracker implements Runnable {
    private final List<NotificationPresenter> observers;
    private final NotificationManager notificationManager;
    private NotificationReader upcomingNotification;

    public NotificationTracker(List<NotificationPresenter> observers,
                               NotificationManager notificationManager) {
        this.observers = observers;
        this.notificationManager = notificationManager;
        this.upcomingNotification = null;
    }

    /**
     * Keep tracking the upcoming notification.
     * If it's time to send the notification,
     * update all observers in observerList with the notification,
     * and then update the upcomingNotification
     */
    @Override
    public void run() {
        while (true) {
            if (upcomingNotification != null) {
                // make a weak condition to send notification
                if (upcomingNotification.getNotificationDateTime().isAfter(LocalDateTime.now())) {
                    updateObservers(upcomingNotification.getMessage());
                    updateUpcomingNotification();
                }
            }
        }

    }

    /***
     * Helper method used by <run> method to update the <upcomingNotification>
     *     after a notification is sent out.
     *     If there is no upcoming notification, set <upcomingNotification>
     *         to be null
     */
    private void updateUpcomingNotification() {
        List<NotificationReader> notificationReaders = notificationManager.getAllNotifications();
        if (notificationReaders.isEmpty()) {
            this.upcomingNotification = null;
        } else {
            NotificationReader earliestSoFar = notificationReaders.get(0);

            for (NotificationReader nr : notificationReaders) {
                if (nr.getNotificationDateTime().isBefore(earliestSoFar.getNotificationDateTime())){
                    earliestSoFar = nr;
                }
            }

            if (earliestSoFar.getNotificationDateTime().isBefore(LocalDateTime.now())) {
                this.upcomingNotification = earliestSoFar;
            } else {
                this.upcomingNotification = null;
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
