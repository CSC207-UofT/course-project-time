package main.java.services.rescheduling;

import main.java.services.event_from_task_creation.EventScheduler;
import main.java.services.event_presentation.EventInfo;

import java.time.LocalDateTime;
import java.util.List;

public interface ReschedulerAuto {
    LocalDateTime getAvailableTime(EventInfo eventInfo, EventScheduler eventScheduler, List<LocalDateTime> timesToIgnore);
}