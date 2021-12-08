package datagateway.event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public interface EventReader {
    long getId();

    String getName();

    Duration getDuration();

    Set<String> getTags();

    Set<LocalDateTime> getDates();

    boolean getCompleted();
}
