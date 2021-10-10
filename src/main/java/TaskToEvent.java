package main.java;

public interface TaskToEvent {
    public Event createEventFromTask(Task task, Schedule schedule)
}
