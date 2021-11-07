package main.java.use_case.notification;

public class NotificationSendingBoundary {
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
