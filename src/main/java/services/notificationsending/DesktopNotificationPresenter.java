package services.notificationsending;

import java.awt.SystemTray;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.AWTException;


/**
 * Sends out desktop push notifications.
 * Keeps a record of whether the user enabled the desktop notification settings.
 */
public class DesktopNotificationPresenter implements NotificationPresenter, SettingsRegistry {
    private boolean enabled;
    private final SystemTray tray = SystemTray.getSystemTray();


    private void presentNotification() {
        try{
            // necessary because you need to instantiate TrayIcon with an image, icon.png doesn't actually exist
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "TIME");
            tray.add(trayIcon);

            // MessageType.INFO is where the icon actually comes from
            trayIcon.displayMessage("TIME", "HI", MessageType.INFO);
        } catch(AWTException e) {
            System.err.print(e.getMessage());
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

    public static void main(String[] args) {
        DesktopNotificationPresenter desktopNotificationPresenter = new DesktopNotificationPresenter();
        desktopNotificationPresenter.presentNotification();
    }
}
