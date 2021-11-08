package main.java.interface_adapters.notification;

public class SystemDecorator extends NotificationBaseDecorator {
    public SystemDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public boolean sendMessage(String message) {
        // TODO: change this to sending out notifications in application
        this.notifier.sendMessage(message);
        return false;
    }
}
