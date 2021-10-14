package main.java;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface EventManager {
    public List<Event> getEvents();

    public boolean createEvent(Task task, LocalDateTime startTime, LocalTime endTime);
}
