package data_gateway;

import java.time.LocalDateTime;

public class NotificationToNotificationReader implements NotificationReader{
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getAssociatedId() {
        return 0;
    }

    @Override
    public LocalDateTime getNotificationDateTime() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
