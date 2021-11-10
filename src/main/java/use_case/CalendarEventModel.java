/*
    Represents a single, non-repeated scheduled event
 */

package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface CalendarEventModel {
    String getName();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    Set<String> getTags();
    LocalDate getDate();

}
