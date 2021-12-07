package datagateway.notification;

import java.util.Map;

public interface SettingsManager {
    void setUserEmail(String newUserEmail);
    String getUserEmail();
    void setNotificationSettings(Map<String, Boolean> newSettings);
    Map<String, Boolean> getNotificationSettings();
}
