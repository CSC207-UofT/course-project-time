package main.java;

public interface TaskToEvent {
    public Event createEventFromTask(Task task, Calendar calendar, EventScheduler eventScheduler);
}
