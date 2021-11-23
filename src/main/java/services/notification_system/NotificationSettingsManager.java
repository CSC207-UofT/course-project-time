package services.notification_system;

import java.util.Map;

public class NotificationSettingsManager implements NotificationSettings{
    private Map<String, Boolean> notificationSettings;

    public NotificationSettingsManager(Map<String, Boolean> notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    public Map<String, Boolean> getNotificationSettings() {
        return notificationSettings;
    }

    @Override
    public void loadNotificationSettings() {

    }

    @Override
    public void saveNewSettings(Map<String, Boolean> newSettings) {

    }
}
