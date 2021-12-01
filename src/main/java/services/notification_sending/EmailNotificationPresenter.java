package services.notification_sending;

/**
 * Sends out email notifications.
 * Keeps a record of whether the user enabled the email notification settings.
 */
public class EmailNotificationPresenter implements NotificationPresenter, SettingsRegistry {
    private boolean enabled;

    @Override
    public void presentNotification(String message) {

    }

    @Override
    public void setSettings(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean getSettings() {
        return this.enabled;
    }
}
