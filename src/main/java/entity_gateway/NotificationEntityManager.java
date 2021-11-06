package main.java.entity_gateway;

import main.java.entity.EventNotification;
import main.java.entity.Notification;
import main.java.entity.TaskNotification;
import main.java.use_case.notification.EventNotificationCreationBoundary;
import main.java.use_case.notification.NotificationCreationBoundary;
import main.java.use_case.notification.TaskNotificationCreationBoundary;

import java.util.ArrayList;
import java.util.List;

public class NotificationEntityManager implements NotificationManager {
    private final List<Notification> notificationList;
    private int counter;

    public NotificationEntityManager(List<Notification> notificationList) {
        this.notificationList = notificationList;
        this.counter = 0;
    }

    @Override
    public int addNotification(NotificationCreationBoundary notifBoundary) {
        Notification notification;
        this.counter += 1;
        if (notifBoundary instanceof EventNotificationCreationBoundary) {
            notification = new EventNotification(this.counter,
                    ((EventNotificationCreationBoundary) notifBoundary).getEventId(),
                    ((EventNotificationCreationBoundary) notifBoundary).getNotificationDurationInAdvance());
        } else if (notifBoundary instanceof TaskNotificationCreationBoundary) {
            notification = new TaskNotification(this.counter,
                    ((TaskNotificationCreationBoundary) notifBoundary).getTaskId(),
                    ((TaskNotificationCreationBoundary) notifBoundary).getNotificationDurationInAdvance());
        }
        return this.counter;
    }

    @Override
    public NotificationReader getNotification(int notifId) {
        for (Notification notif: notificationList) {
            if (notif.getId() == notifId) {
                return new NotifToNotifReader(notif);
            }
        }
    }

    @Override
    public List<NotificationReader> getAllNotifications() {
        List<NotificationReader> notificationReaderList = new ArrayList<>();
        for (Notification notif: notificationList) {
            notificationReaderList.add(new NotifToNotifReader(notif));
        }
        return notificationReaderList;
    }

    @Override
    public boolean deleteNotification(int notifId) {
        for (Notification notif: notificationList) {
            if (notif.getId() == notifId) {
                return true;
            }
        }
        return false;
    }
}
