package services.event_from_task_creation;

import console_app.event_adapters.CalendarEventData;
import data_gateway.CalendarManager;
import data_gateway.TaskReader;
import data_gateway.TodoListManager;
import services.event_creation.CalendarEventModel;

public class EventFromTaskCreator implements EventFromTaskCreatorBoundary {

    private final TodoListManager todoListManager;
    private final CalendarManager calendarManager;

    public EventFromTaskCreator(TodoListManager todoListManager, CalendarManager calendarManager) {
        this.todoListManager = todoListManager;
        this.calendarManager = calendarManager;
    }

    @Override
    public boolean createEventFromTask(EventFromTaskModel eventData) {
        TaskReader tr = todoListManager.getTask(0, eventData.getTaskId());
        CalendarEventModel eventModel = new CalendarEventData(tr.getName(), eventData.getStartTime(), eventData.getStartTime().plus(tr.getDuration()), eventData.getTags());
        calendarManager.addEvent(eventModel);
        return true;
    }

}
