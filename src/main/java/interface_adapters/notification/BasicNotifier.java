package main.java.interface_adapters.notification;

public class BasicNotifier implements Notifier {
    @Override
    public boolean sendMessage(String message) {
        return false;
    }
}
