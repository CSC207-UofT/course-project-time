package services.notification_sending;

/**
 * Sends out desktop push notifications.
 * Keeps a record of whether the user enabled the desktop notification settings.
 */
public class DesktopNotificationPresenter implements NotificationPresenter, SettingsRegistry {
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
