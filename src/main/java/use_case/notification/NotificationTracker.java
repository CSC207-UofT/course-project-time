package main.java.use_case.notification;

import main.java.entity.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationTracker implements INotificationTracker, Runnable{

    private final List<NotificationObserver> observerList;
    private final List<Notification> notificationList;

    public NotificationTracker(List<NotificationObserver> observers) {
        this.observerList = observers;
        this.notificationList = new ArrayList<>();
    }

    public boolean addNotifications(List<Notification> unsortedNotifications) {
        for (Notification notification : unsortedNotifications) {
            if (!addNotification(notification)) {
                return false;
            }
        }
        return true;
    }

    // TODO: should this method be private?
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

    public boolean deleteNotification(int id, LocalDateTime time) {
        return notificationList.removeIf(notification ->
                id == notification.getAssociatedId() && time == notification.getNotificationTime());
    }

    public boolean deleteNotifications(int id) {
        return notificationList.removeIf(notification -> id == notification.getAssociatedId());
    }

    @Override
    public boolean updateAll(Notification notification) {
        for (NotificationObserver observer : observerList) {
            if (!observer.update(notification)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
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
