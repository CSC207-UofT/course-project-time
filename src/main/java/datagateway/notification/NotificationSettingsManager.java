package datagateway.notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationSettingsManager implements SettingsManager {
    private String email;
    private final Map<String, Boolean> notificationSettings;

    public NotificationSettingsManager() {
        this.email = "testtime@gmail.com";
        notificationSettings = new HashMap<>();
        notificationSettings.put("email", true);
        notificationSettings.put("desktop", true);
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Map<String, Boolean> getNotificationSettings() {
        return notificationSettings;
    }
}
