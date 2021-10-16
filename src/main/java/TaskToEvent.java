package main.java;

public interface TaskToEvent {
    Event createEventFromTask(Task task, Calendar calendar, EventScheduler eventScheduler);
}
