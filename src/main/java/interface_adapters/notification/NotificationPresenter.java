package main.java.interface_adapters.notification;

import main.java.use_case.notification.NotificationSender;

public class NotificationPresenter implements NotificationSender {

    private Notifier notifier;

    public NotificationPresenter(NotificationSettings settings) {
        resetNotification(settings);
    }

    public void resetNotification(NotificationSettings settings) {
        this.notifier = new BasicNotifier();
        if (settings.isEmailEnabled()) {
            notifier = new EmailDecorator(notifier);
        }
        if (settings.isSystemEnabled()) {
            notifier = new SystemDecorator(notifier);
        }
    }

    @Override
    public boolean sendMessage(String message) {
        return notifier.sendMessage(message);
    }
}
