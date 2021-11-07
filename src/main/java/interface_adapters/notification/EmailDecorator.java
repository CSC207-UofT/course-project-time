package main.java.interface_adapters.notification;

public class EmailDecorator extends NotificationBaseDecorator {
    public EmailDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void sendMessage(String message) {
        // TODO: change this to send email in phase 2
        this.notifier.sendMessage(message);
    }
}
