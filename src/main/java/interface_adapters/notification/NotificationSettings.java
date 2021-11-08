package main.java.interface_adapters.notification;

public class NotificationSettings {
    private boolean emailEnabled;
    private boolean systemEnabled;
    private String message;

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public boolean isSystemEnabled() {
        return systemEnabled;
    }

    public String getMessage() {
        return message;
    }
}
