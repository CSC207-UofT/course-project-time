package main.java.interface_adapters.notification;

public class NotificationSettings {
    private boolean emailEnabled;
    private boolean systemEnabled;

    public NotificationSettings() {
        this.emailEnabled = false;
        this.systemEnabled = true;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public void setSystemEnabled(boolean systemEnabled) {
        this.systemEnabled = systemEnabled;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public boolean isSystemEnabled() {
        return systemEnabled;
    }

}
