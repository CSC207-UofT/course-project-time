package main.java.use_case;

import java.time.LocalDateTime;
import java.util.Set;

public interface EventInfo {
    String getName();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    Set<String> getTags();

    LocalDateTime getDates();
}
