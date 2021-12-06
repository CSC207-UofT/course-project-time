package services.notification_sending;

/**
 * Classes that extends this interface keeps a record of
 * a particular settings.
 */
public interface SettingsRegistry {
    void setSettings(boolean enabled);

    boolean getSettings();
}
