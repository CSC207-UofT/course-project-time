package main.java.interface_adapters.notification;

import main.java.entity.Notification;

public class BasicNotifier implements Notifier {
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
