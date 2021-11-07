package main.java.interface_adapters.notification;

public abstract class NotificationBaseDecorator implements Notifier {
    protected Notifier notifier;

    public NotificationBaseDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    public void sendMessage(String message) {
        this.notifier.sendMessage(message);
    }
}
