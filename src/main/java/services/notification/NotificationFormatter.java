package services.notification;

import services.event_creation.CalendarEventModel;
import services.task_creation.TodoListTaskCreationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NotificationFormatter implements NotificationFormat{
    @Override
    public String formatNotificationMessage(CalendarEventModel eventModel) {
        return null;
    }

    @Override
    public String formatNotificationMessage(TodoListTaskCreationModel taskModel) {
        return null;
    }

    /***
     * Generate a task notification message with the format
     *          You have an upcoming task <task name> due on <deadline>
     * @param taskName the name of the task
     * @param deadline the deadline of the task
     * @return the task notification message
     */
    @Override
    public String formatTaskNotificationMessage(String taskName, LocalDateTime deadline) {
        return "You have an upcoming task due at" + deadline.toString();
    }

    /***
     * Generate an event notification message with the format
     *          You have an upcoming event <event name> from <start time> to <end time> on <date>.
     * @param eventName the name of the event
     * @param date the date of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @return the event notification message
     */
    @Override
    public String formatEventNotificationMessage(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return "You have an upcoming event " + eventName +
                " from " + startTime.toString() +
                " to " + endTime.toString() +
                " on " + date.toString();
    }
}
