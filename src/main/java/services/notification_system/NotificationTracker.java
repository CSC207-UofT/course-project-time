package services.notification_system;

import entity.Notification;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationTracker implements Runnable, NotificationTrack{
    private List<NotificationPresenter> observers;
    private List<Notification> notificationList;

    public NotificationTracker(List<NotificationPresenter> observers, List<Notification> notificationList) {
        this.observers = observers;
        this.notificationList = notificationList;
    }

    /**
     * Delete a notification in notificationList with
     * associatedId being id and notificationTime being time
     * @param id the associatedId of the target notification
     * @param time the notification time of the target notification
     * @return true if the corresponding notification is deleted successfully
     */
    public void deleteNotification(int id, LocalDateTime time) {
        notificationList.removeIf(notification ->
                id == notification.getAssociatedId() && time == notification.getNotificationDateTime());
    }

    /**
     * Delete all notifications in notificationList with associatedId being id
     * @param id the associated id that decide which notifications to delete
     * @return true if all notifications are deleted successfully
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

    /**
     * Keep tracking the earliest notification.
     * If it's time to send the notification,
     * update all observers in observerList with the notification,
     * and then remove this notification from notificationList
     */
    @Override
    public void run() {
        // TODO: rerun the run() when notificationList is no longer empty
        while (!notificationList.isEmpty()) {
            // remove overdue notifications
            if (notificationList.get(0).getNotificationDateTime().isAfter(LocalDateTime.now())) {
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
            observer.presentNotifications(message);
        }
    }
}
