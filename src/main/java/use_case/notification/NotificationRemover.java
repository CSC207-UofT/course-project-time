package main.java.use_case.notification;

import main.java.constants.NotificationType;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventReader;
import main.java.entity_gateway.TaskReader;
import main.java.entity_gateway.TodoListManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotificationRemover {
    private final NotificationTracker notificationTracker;
    private final CalendarManager calendarManager;
    private final TodoListManager todoListManager;

    public NotificationRemover(NotificationTracker notificationTracker,
                             CalendarManager calendarManager,
                             TodoListManager todoListManager) {
        this.notificationTracker = notificationTracker;
        this.calendarManager = calendarManager;
        this.todoListManager = todoListManager;
    }

    /**
     * Deletes a notification, given the relevant information through boundary.
     * @param boundary  contains relevant information about the notification to be deleted
     * @return whether the notification has been deleted successfully
     */
    public boolean deleteNotification(NotificationCreationBoundary boundary) {
        int id = boundary.getIdOfAssociatedObject();
        if (boundary.getType() == NotificationType.Event) {
            EventReader eventReader = this.calendarManager.getEvent(id);
            for (LocalDate date : eventReader.getDates()) {
                LocalDateTime notifDateTime = date.atTime(eventReader.getStartTime())
                                                .plus(boundary.getNotificationDurationInAdvance());
                if (!this.notificationTracker.deleteNotification(id, notifDateTime)) {
                    return false;
                }
            }
            return true;
        } else if (boundary.getType() == NotificationType.Task) {
            TaskReader taskReader = this.todoListManager.getTask(id);
            return this.notificationTracker.deleteNotification(id,
                    taskReader.getDeadline().plus(boundary.getNotificationDurationInAdvance()));
        }
        return false;
    }

    /**
     * Delete all notifications associated with the object, given the object's id.
     * The object could be an event, task, etc.
     * @param idOfAssociatedObject      id of the associated object whose notifications are to be deleted
     * @return whether the notifications have been deleted successfully
     */
    public boolean deleteNotifications(int idOfAssociatedObject) {
        return this.notificationTracker.deleteNotifications(idOfAssociatedObject);
    }
}
