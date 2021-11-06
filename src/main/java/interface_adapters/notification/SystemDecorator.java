package main.java.interface_adapters.notification;

import main.java.entity.Notification;
import main.java.interface_adapters.notification.NotificationBaseDecorator;
import main.java.interface_adapters.notification.Notifier;

public class SystemDecorator extends NotificationBaseDecorator {
    public SystemDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void sendMessage(Notification notification) {

    }
}
