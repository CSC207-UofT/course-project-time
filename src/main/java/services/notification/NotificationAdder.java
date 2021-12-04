package services.notification;

import services.event_creation.CalendarEventModel;
import services.task_creation.TodoListTaskCreationModel;

public class NotificationAdder {

    /***
     * Create an event notification using the date from eventData and the event id eventId
     * @param eventData the event data from the user
     * @param eventId the id of the event
     */
    public void createNotification(CalendarEventModel eventData, long eventId) {
    }

    /***
     * Create a task notification using the date from taskData and the task id tasktId
     * @param taskData the task data from the user
     * @param taskId the id of the task
     */
    public void createNotification(TodoListTaskCreationModel taskData, long taskId) {
    }
}
