package services.notification_sending;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

/**
 * Sends out desktop push notifications.
 * Keeps a record of whether the user enabled the desktop notification settings.
 */
public class DesktopNotificationPresenter implements NotificationPresenter, SettingsRegistry {
    private boolean enabled;
    private SystemTray tray = SystemTray.getSystemTray();

    @Override
    public void presentNotification(String message) {
        try{
            Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/resources/icon.png"));
            TrayIcon trayIcon = new TrayIcon(image, "Time");
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);
            trayIcon.displayMessage("Time", message, MessageType.INFO);
        } catch(AWTException e) {
            System.err.print(e);
        }

    }

    @Override
    public void setSettings(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean getSettings() {
        return this.enabled;
    }
}
