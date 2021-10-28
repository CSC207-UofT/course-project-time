package main.java.use_case;

import main.java.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskToEventAuto {

    LocalDateTime getAvailableTime(Task task, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes);
}
