package main.java.use_case.notification;

import main.java.entity.Notification;

public interface INotificationObserver {
    boolean update(Notification notification);
}
