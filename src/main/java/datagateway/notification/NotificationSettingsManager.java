package datagateway.notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationSettingsManager implements SettingsManager {
    private String userEmail;
    private Map<String, Boolean> notificationSettings;

    public NotificationSettingsManager() {
        this.userEmail = "testtime@gmail.com";
        notificationSettings = new HashMap<>();
        notificationSettings.put("email", true);
        notificationSettings.put("desktop", true);
    }

    @Override
    public void setUserEmail(String newUserEmail) {
        this.userEmail = newUserEmail;
    }

    @Override
    public String getUserEmail() {
        return this.userEmail;
    }

    @Override
    public void setNotificationSettings(Map<String, Boolean> newSettings) {
        this.notificationSettings = newSettings;
    }

    @Override
    public Map<String, Boolean> getNotificationSettings() {
        return notificationSettings;
    }
}
