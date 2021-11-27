package data_gateway;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationEntityManager implements NotificationManager{
    @Override
    public void addNotification(long associatedId, LocalDateTime NotificationDateTime, String message) {

    }

    @Override
    public List<NotificationReader> getAllNotifications() {
        return null;
    }

    @Override
    public void loadNotifications(String filePath) throws IOException {

    }

    @Override
    public void saveNotifications(String filePath) throws IOException {

    }
}
