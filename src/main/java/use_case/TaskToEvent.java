package main.java.use_case;

import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskToEvent {

    LocalDateTime getAvailableTime(Task task, Calendar calendar, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes);
}
