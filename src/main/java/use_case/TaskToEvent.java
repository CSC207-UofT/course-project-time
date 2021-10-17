package main.java.use_case;

import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;

public interface TaskToEvent {
    Event createEventFromTask(Task task, Calendar calendar, EventScheduler eventScheduler);
}
