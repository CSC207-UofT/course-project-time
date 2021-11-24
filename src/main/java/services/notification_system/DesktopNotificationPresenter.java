package services.notification_system;

public class DesktopNotificationPresenter implements NotificationPresenter{
    private boolean enabled;

    @Override
    public void presentNotifications(String message) {

    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
