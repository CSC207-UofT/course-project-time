package datagateway.notification;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.Notification;
import services.Snowflake;
import services.notification.NotificationCreationModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NotificationEntityManager implements NotificationManager {
    private final List<Notification> notifications;
    private final Gson gson;

    public NotificationEntityManager(Snowflake snowflake) {
        this.notifications = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Notification.class, new JsonNotificationAdapter());
        gson = builder.create();
    }

    /**
     * Adds a notification to the database.
     * @param model the DTO that contains all the information to create a notification
     */
    @Override
    public void addNotification(NotificationCreationModel model) {
        Notification notification = new Notification(model.getAssociatedId(), model.getTimeInAdvance(),
                model.getNotificationDateTime(), model.getMessage());

        notifications.add(notification);
    }

    /**
     * Deletes a notification
     * @param associatedId the id of the associated object that the notification is for
     * @param timeInAdvance the duration in advance that the notification is to be sent out
     */
    @Override
    public void deleteNotification(long associatedId, Duration timeInAdvance) {
        for (Notification notification : notifications) {
            if (notification.getAssociatedId() == associatedId
                    && notification.getNotificationTimeInAdvance().equals(timeInAdvance)) {
                notifications.remove(notification);
                break;
            }
        }
    }

    /***
     * Deletes notifications with associatedId
     * @param associatedId the id of the associated object that the notification is for
     */
    @Override
    public void deleteNotification(long associatedId) {
        notifications.removeIf(notification -> notification.getAssociatedId() == associatedId);
    }

    /**
     * @return all notifications
     */
    @Override
    public List<NotificationReader> getAllNotifications() {
        List<NotificationReader> notificationReaderList = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationReaderList.add(new NotificationToNotificationReader(notification));
        }
        return notificationReaderList;
    }

    /**
     * Retrieve all notifications that belong ot the object as specified to the id
     * @param associatedId the id of the object that we want to get the notifications of
     * @return notifications belonging to the specified object
     */
    @Override
    public List<NotificationReader> getNotificationsForAssociatedObject(long associatedId) {
        List<NotificationReader> notificationReaderList = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getAssociatedId() == associatedId) {
                notificationReaderList.add(new NotificationToNotificationReader(notification));
            }
        }
        return notificationReaderList;
    }

    /**
     * Return a notification.
     * @param associatedId the id of the associated object that the notification is for
     * @param timeInAdvance the duration in advance that the notification is to be sent out
     * @return the corresponding notification
     */
    @Override
    public NotificationReader getNotification(long associatedId, Duration timeInAdvance) {
        for (Notification notification : notifications) {
            if (notification.getAssociatedId() == associatedId
                    && notification.getNotificationTimeInAdvance().equals(timeInAdvance)) {
                return new NotificationToNotificationReader(notification);
            }
        }
        return null;
    }

    /**
     * Loads notification data from specified json file
     * @param filePath The location of the json file containing notification data
     * @throws FileNotFoundException if the specified file cannot be accessed
     */
    @Override
    public void loadNotifications(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.isFile()) {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Type listType = new TypeToken<List<Notification>>(){}.getType();
            List<Notification> notifications = gson.fromJson(reader, listType);

            if(notifications != null) {
                this.notifications.addAll(notifications);
            }
            reader.close();
        }
    }

    /**
     * Saves notification data into json file
     * @param filePath The location of the json file containing event data
     * @throws FileNotFoundException if the specified file cannot be accessed
     */
    @Override
    public void saveNotifications(String filePath) throws IOException {
        FileWriter fw = new FileWriter("NotificationData.json");
        String cal_json = gson.toJson(this.notifications);
        fw.write(cal_json);
        fw.close();
    }
}
