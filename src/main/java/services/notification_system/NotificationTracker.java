package services.notification_system;

import entity.Notification;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationTracker implements Runnable, NotificationTrack{
    private List<NotificationPresenter> observers;
    private List<Notification> notificationList;

    private void sendAll(String message) {

    }

    private void sortNotifications() {

    }

    public void run(){

    }

    public void deleteNotifications(long id){

    }

    public void deleteNotification(long id, LocalDateTime dateTime){

    }

    public void addNotification(Notification notification) {

    }

    public void updateObservers(NotificationSettings notificationSettings) {

    }

}
