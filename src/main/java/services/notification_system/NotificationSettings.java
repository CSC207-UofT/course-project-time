package services.notification_system;

import java.util.Map;

public interface NotificationSettings {
    Map<String, Boolean> getNotificationSettings();

    void loadNotificationSettings();
    void saveNewSettings(Map<String, Boolean> newSettings);
}
