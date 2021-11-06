package main.java.interface_adapters.notification;

import main.java.entity.Notification;

public class EmailDecorator extends NotificationBaseDecorator {
    public EmailDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void sendMessage(Notification notification) {

    }
}
