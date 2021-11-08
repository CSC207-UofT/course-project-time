package main.java.use_case.notification;

import main.java.entity.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Runnable NotificationTracker that track the upcoming notifications
 *
 * - observerList: the observers that observe and handle new notifications
 * - notificationList: a list of notification that will be sent to user,
 *                     sorted based on the notification time, from earlier to later
 */
public class NotificationTracker implements INotificationTracker, Runnable{

    private final List<NotificationObserver> observerList;
    private final List<Notification> notificationList;

    public NotificationTracker(List<NotificationObserver> observers) {
        this.observerList = observers;
        this.notificationList = new ArrayList<>();
    }

    /**
     * Add a list of unsorted notifications to notificationList.
     * This method is always called when starting the program and
     * importing the existing notifications
     * @param unsortedNotifications a unsorted notification list
     * @return true if all notifications are added successfully
     */
    public boolean addNotifications(List<Notification> unsortedNotifications) {
        for (Notification notification : unsortedNotifications) {
            if (!addNotification(notification)) {
                return false;
            }
        }
        return true;
    }

    // TODO: should this method be private?
    /**
     * Insert a notification to notificationList, based on the notification time.
     * @param newNotification the notification that will be added
     * @return true if the new notification is added successfully
     */
    public boolean addNotification(Notification newNotification) {
        LocalDateTime newNotificationTime = newNotification.getNotificationTime();
        for (int i = 0; i < notificationList.size(); i++) {
            if (newNotificationTime.isBefore(notificationList.get(i).getNotificationTime())) {
                notificationList.add(i, newNotification);
                return true;
            }
        }
        // either <notifications> is empty or it has been checked to the end
        notificationList.add(newNotification);
        return true;
    }

    /**
     * Delete a notification in notificationList with
     * associatedId being id and notificationTime being time
     * @param id the associatedId of the target notification
     * @param time the notification time of the target notification
     * @return true if the corresponding notification is deleted successfully
     */
    public boolean deleteNotification(int id, LocalDateTime time) {
        return notificationList.removeIf(notification ->
                id == notification.getAssociatedId() && time == notification.getNotificationTime());
    }

    /**
     * Delete all notifications in notificationList with associatedId being id
     * @param id the associated id that decide which notifications to delete
     * @return true if all notifications are deleted successfully
     */
    public boolean deleteNotifications(int id) {
        return notificationList.removeIf(notification -> id == notification.getAssociatedId());
    }

    /**
     * update the coming notification to all observers in the observerList
     * @param notification the coming notification that needs to be sent out
     */
    @Override
    public void updateAll(Notification notification) {
        for (NotificationObserver observer : observerList) {
            if (!observer.update(notification)) {
                return;
            }
        }
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
            if (notificationList.get(0).getNotificationTime().isAfter(LocalDateTime.now())) {
                notificationList.remove(0);
            } else if (notificationList.get(0).getNotificationTime().equals(LocalDateTime.now())) {
                updateAll(notificationList.get(0));
                notificationList.remove(0);
            }
        }
    }
}
