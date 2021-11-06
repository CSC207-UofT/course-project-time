package main.java.entity_gateway;

import java.time.LocalDateTime;

public interface NotificationReader {
    int getId();

    LocalDateTime getNotifTime();

    int getIdOfAssociatedObject();
}
