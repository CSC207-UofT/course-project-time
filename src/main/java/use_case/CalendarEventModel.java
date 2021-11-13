/*
    Represents a single, non-repeated scheduled event
 */

package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public interface CalendarEventModel {
    public String getName();
    public LocalDateTime getStartTime();
    public LocalDateTime getEndTime();
    public HashSet<String> getTags();
    public LocalDate getDate();

}
