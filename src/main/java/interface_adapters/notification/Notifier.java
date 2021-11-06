package main.java.interface_adapters.notification;

import main.java.entity.Notification;

public interface Notifier {
    public void sendMessage(Notification notification);
}
