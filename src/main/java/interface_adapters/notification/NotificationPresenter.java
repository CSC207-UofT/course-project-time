package main.java.interface_adapters.notification;

import main.java.use_case.notification.NotificationSendingBoundary;

public class NotificationPresenter {
    public void sendMessage(NotificationSendingBoundary boundary) {
        Notifier notifier = new BasicNotifier();
        if (boundary.isEmailEnabled()) {
            notifier = new EmailDecorator(notifier);
        }
        if (boundary.isSystemEnabled()) {
            notifier = new SystemDecorator(notifier);
        }

        notifier.sendMessage(boundary.getMessage());
    }
}
