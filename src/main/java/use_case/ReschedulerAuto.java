package main.java.use_case;

import java.time.LocalDateTime;
import java.util.List;

public interface ReschedulerAuto {
    LocalDateTime getAvailableTime(EventInfo eventInfo, EventScheduler eventScheduler, List<LocalDateTime> timesToIgnore);
}