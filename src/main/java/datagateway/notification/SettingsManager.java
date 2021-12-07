package datagateway.notification;

import java.util.Map;

public interface SettingsManager {
    String getEmail();
    Map<String, Boolean> getNotificationSettings();
}
