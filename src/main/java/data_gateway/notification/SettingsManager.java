package data_gateway.notification;

import java.util.Map;

public interface SettingsManager {
    String getEmail();
    Map<String, Boolean> getNotificationSettings();
}
