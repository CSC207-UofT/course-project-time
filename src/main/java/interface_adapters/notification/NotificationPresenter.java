package main.java.interface_adapters.notification;

import main.java.use_case.notification.NotificationSender;

/**
 * NotificationPresents is used by NotificationObserver
 * to send notification message to user
 *
 * - notifier: used to send message, which is
 *             constructed based on the notification settings
 */
public class NotificationPresenter implements NotificationSender {

    private Notifier notifier;

    public NotificationPresenter(NotificationSettings settings) {
        resetNotification(settings);
    }

    /**
     * reset the notification settings by rebuilding the notifier
     * @param settings new notification settings
     */
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
