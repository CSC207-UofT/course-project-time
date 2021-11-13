package main.java.use_case;

import java.time.LocalDateTime;
import java.util.List;

public interface RescheduleAuto {
    LocalDateTime getAvailableTime(TaskInfo taskInfo, EventScheduler eventScheduler, List<LocalDateTime> timesToIgnore);
}
