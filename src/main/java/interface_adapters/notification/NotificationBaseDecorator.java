package main.java.interface_adapters.notification;

import main.java.entity.Notification;

public abstract class NotificationBaseDecorator implements Notifier {
    protected Notifier notifier;

    public NotificationBaseDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    public void sendMessage(Notification notification) {
        this.notifier.sendMessage(notification);
    }
}
