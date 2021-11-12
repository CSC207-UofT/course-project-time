package main.java.use_case.notification;

import main.java.entity.Notification;

public interface NotificationObserverInterface {
    boolean update(Notification notification);
}
