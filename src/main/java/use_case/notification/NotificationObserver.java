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

/**
 * NotificationObserver is responsible for receiving and
 * processing notification command from NotificationTracker.
 * NotificationObserver will organize the message format based
 * on the NotificationType of notifications.
 *
 * - sender: responsible for sending the message text to the user on several platforms
 * - calendarManager: used for searching events based on event ids
 * - todoListManager: used for searching tasks based on task ids
 */
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

    /**
     * Receive new notification command and send it out
     * @param notification the new notification that will be sent out
     * @return true if the message is sent out successfully
     */
    @Override
    public boolean update(Notification notification) {
        if (notification.getNotificationType() == NotificationType.Task) {
            int taskId = notification.getAssociatedId();
            String message = taskMessage(taskId);
            return sender.sendMessage(message);
        } else if (notification.getNotificationType() == NotificationType.Event) {
            int eventId = notification.getAssociatedId();
            String message = eventMessage(eventId);
            return sender.sendMessage(message);
        }
        return false;
    }

    /**
     * Search for information of a task using the task id,
     * and generate message text for this task:
     *
     * "You have an upcoming event <taskName> due on <duedate> at <dueTime>."
     *
     * @param taskId the id of the task
     * @return string of message text for the task
     */
    private String taskMessage(int taskId) {
        TaskReader taskReader = todoListManager.getTask(taskId);
        String taskName = taskReader.getName();
        LocalDate dueDate = taskReader.getDeadline().toLocalDate();
        LocalTime dueTime = taskReader.getDeadline().toLocalTime();
        String message = "You have a task " + taskName + " due on " + dueDate + " at " + dueTime + ".";
        return message;
    }

    /**
     * Search for information of an event using the event id,
     * and generate message text for this event:
     *
     * "You have an upcoming event <eventName> from <startTime> to < endTime> on <date>."
     *
     * @param eventId the id of the event
     * @return string of message text for the event
     */
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
                " from " + startTime + " to " + endTime + " on " + date + ".";
        return message;

    }

    /**
     * Search for the date an event notification aimed for.
     * @param durations set of duration times of the notifications of the event
     * @param dates set of recurring dates of the events
     * @param currentDateTime the time when the notification is sent
     * @return the date found
     */
    private LocalDate searchEventDate(Set<Duration> durations,
                                      Set<LocalDate> dates, LocalDateTime currentDateTime) {
        // TODO: return something out of for loop or use exception
        for (Duration notificationTime : durations) {
            for (LocalDate date : dates) {
                if (currentDateTime.plus(notificationTime).toLocalDate().equals(date)) {
                    return date;
                }
            }
        }
        return currentDateTime.toLocalDate();
    }
}
