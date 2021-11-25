package services.notification_system;

import java.util.HashMap;
import java.util.Map;

public class NotificationSettingsManager implements NotificationSettings{
    private Map<String, Boolean> notificationSettings;

    public NotificationSettingsManager() {
        loadNotificationSettings();
    }

    public Map<String, Boolean> getNotificationSettings() {
        return notificationSettings;
    }

    /***
     * Load notification settings from the database
     */
    @Override
    public void loadNotificationSettings() {
        notificationSettings = new HashMap<>();
        notificationSettings.put("EmailEnabled", true);
        notificationSettings.put("DesktopEnabled", true);
        // TODO: Load settings from the database
    }

    /***
     * Update notificationSettings and save the new setting to the data base
     * @param newSettings the new notification settings
     */
    @Override
    public void saveNewSettings(Map<String, Boolean> newSettings) {
        notificationSettings = newSettings;
        // TODO: save to the datebase
    }
}
