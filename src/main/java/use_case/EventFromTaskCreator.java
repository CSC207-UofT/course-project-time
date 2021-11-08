package main.java.use_case;

import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.TaskReader;
import main.java.entity_gateway.TodoListManager;

public class EventFromTaskCreator implements EventFromTaskCreatorBoundary {

    private final TodoListManager todoListManager;
    private final CalendarManager calendarManager;

    public EventFromTaskCreator(TodoListManager todoListManager, CalendarManager calendarManager) {
        this.todoListManager = todoListManager;
        this.calendarManager = calendarManager;
    }

    public boolean createEventFromTask(EventFromTaskModel eventData) {
        TaskReader tr = todoListManager.getTask(0, eventData.getTaskId());
        CalendarEventModel eventModel = new CalendarEventData(tr.getName(), eventData.getStartTime(), eventData.getStartTime().plus(tr.getDuration()), eventData.getTags(), eventData.getDates());
        calendarManager.addEvent(eventModel);
        return true;
    }

}
