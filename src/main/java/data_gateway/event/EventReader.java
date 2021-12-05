package data_gateway.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface EventReader {
    long getId();

    String getName();

    LocalTime getStartTime();

    LocalTime getEndTime();

    Set<String> getTags();

    Set<LocalDate> getDates();

    boolean getCompleted();
}
