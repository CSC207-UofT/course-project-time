package services.notification_system;

import java.util.List;

public interface NotificationTrack {
    List<NotificationPresenter> observers = null;

    void updateObservers(NotificationSettings notificationSettings);
}
