package main.java.use_case;

import main.java.entity.Calendar;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskToEventAuto {

    LocalDateTime getAvailableTime(TaskInfo task, Calendar calendar, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes);
}
