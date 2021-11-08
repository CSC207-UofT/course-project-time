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
import java.util.Set;

public class NotificationObserver implements INotificationObserver {

    private NotificationSender sender;
    private CalendarManager calendarManager;
    private TodoListManager todoListManager;

    public NotificationObserver(NotificationSender sender, CalendarManager calendarManager,
                                TodoListManager todoListManager) {
        this.sender = sender;
        this.calendarManager = calendarManager;
        this.todoListManager = todoListManager;
    }

    @Override
    public boolean update(Notification notification) {
        if (notification.getType() == NotificationType.Task) {
            int taskId = notification.getAssociatedId();
            String message = taskMessage(taskId);
            return sender.sendMessage(message);
        } else if (notification.getType() == NotificationType.Event) {
            int eventId = notification.getAssociatedId();
            String message = eventMessage(eventId);
            return sender.sendMessage(message);
        }

    }

    private String taskMessage(int taskId) {
        TaskReader taskReader = todoListManager.getTask(taskId);
        String taskName = taskReader.getName();
        LocalDate dueDay = taskReader.getDeadline().toLocalDate();
        LocalTime dueTime = taskReader.getDeadline().toLocalTime();
        String message = "You have a task " + taskName + " due on " + dueDay + " at " + dueTime;
        return message;
    }

    private String eventMessage(int eventId) {
        EventReader eventReader = calendarManager.getEvent(eventId);
        String eventName = eventReader.getName();
        LocalTime startTime = eventReader.getStartTime();
        LocalTime endTime = eventReader.getEndTime();
        Set<LocalDate> dates = eventReader.getDates();
        Set<Duration> notificationTimes = eventReader.getNotificationTimes();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate date = searchEventDate(notificationTimes, dates, currentDateTime);
        String message = "You have an upcoming event " + eventName +
                " from " + startTime + " to " + endTime + " on " + date;
        return message;

    }

    private LocalDate searchEventDate(Set<Duration> durations, Set<LocalDate> dates, LocalDateTime currentDateTime) {
        for (Duration notificationTime : durations) {
            for (LocalDate date : dates) {
                if (currentDateTime.plus(notificationTime).toLocalDate().equals(date)) {
                    return date;
                }
            }
        }
    }
}
