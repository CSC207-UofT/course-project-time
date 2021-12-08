package services.notification;

import services.eventcreation.CalendarEventModel;
import services.eventcreation.EventFromTaskModel;
import services.taskcreation.TodoListTaskCreationModel;

public class NotificationAdder {

    /***
     * Create an event notification using the date from eventData and the event id eventId
     * @param eventData the event data passed down from the user interface
     *                  when the user creates a new event
     * @param eventId the id of the event
     */
    public void createNotification(CalendarEventModel eventData, long eventId) {
    }

    public void createNotification(EventFromTaskModel eventData, long eventId) {
    }

    /***
     * Create a task notification using the date from taskData and the task id tasktId
     * @param taskData the task data passed down from the user interface
     *                 when the user creates a new event
     * @param taskId the id of the task
     */
    public void createNotification(TodoListTaskCreationModel taskData, long taskId) {
    }
}
