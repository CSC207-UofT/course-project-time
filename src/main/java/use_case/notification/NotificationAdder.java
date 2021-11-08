package main.java.use_case.notification;

import main.java.constants.NotificationType;
import main.java.entity.Notification;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventReader;
import main.java.entity_gateway.TaskReader;
import main.java.entity_gateway.TodoListManager;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationAdder {
    private final NotificationTracker notificationTracker;
    private final CalendarManager calendarManager;
    private final TodoListManager todoListManager;

    public NotificationAdder(NotificationTracker notificationTracker,
                             CalendarManager calendarManager,
                             TodoListManager todoListManager) {
        this.notificationTracker = notificationTracker;
        this.calendarManager = calendarManager;
        this.todoListManager = todoListManager;
    }

    /**
     * Creates new notifications and add them to the notification tracker.
     * @param notifBoundary object containing all necessary information to create a new notification
     * @return whether the notifications are created successfully
     */
    public boolean createNotifications(NotificationCreationBoundary notifBoundary) {
        List<Notification> notificationList = new ArrayList<>();

        Duration duration = notifBoundary.getNotificationDurationInAdvance();
        NotificationType notificationType = notifBoundary.getType();
        int id = notifBoundary.getIdOfAssociatedObject();

        if (notificationType == NotificationType.Task) {
            LocalDateTime datetime = this.todoListManager.getTask(id).getDeadline();
            Notification notification = new Notification(notificationType, id, datetime.plus(duration));
            notificationList.add(notification);
        } else if (notificationType == NotificationType.Event) {
            EventReader eventReader = this.calendarManager.getEvent(id);
            LocalTime startTime = eventReader.getStartTime();
            for (LocalDate date : eventReader.getDates()) {
                Notification notification = new Notification(notificationType, id, date.atTime(startTime).plus(duration));
                notificationList.add(notification);
            }
        }

        return this.notificationTracker.addNotifications(notificationList);
    }

    /**
     * Goes through all events, tasks, etc. to find existing notifications. Add these notifications
     * to the NotificationTracker. Should only be called once at runtime.
     * @return whether the notifications are populated successfully
     */
    public boolean populateTrackerWithNotifications() {
        List<Notification> notificationList = new ArrayList<>();
        List<EventReader> eventReaders = this.calendarManager.getAllEvents();
        for (EventReader eventReader : eventReaders) {
            for (LocalDate date : eventReader.getDates()) {
                Notification notification = new Notification(NotificationType.Event,
                                                            eventReader.getId(),
                                                            date.atTime(eventReader.getStartTime()));
                notificationList.add(notification);
            }
        }
        List<TaskReader> taskReaders = this.todoListManager.getAllTasksInList();
        for (TaskReader taskReader : taskReaders) {
            Notification notification = new Notification(NotificationType.Task, taskReader.getId(), taskReader.getDeadline());
            notificationList.add(notification);
        }

        return this.notificationTracker.addNotifications(notificationList);
    }
}
