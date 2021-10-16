package main.java;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Sorts times to search for the earliest available gap.
 * Assumes that times do not overlap.
 */
public class SortAndSearch implements GapFinder {
    public LocalDateTime findTimeGap(List<TimeFrame> timeFramesToIgnore, Duration taskDuration) {
        Collections.sort(timeFramesToIgnore);
        TimeFrame first, second = null;
        for (TimeFrame t : timeFramesToIgnore) {
            first = second;
            second = t;
            if (first != null && first.end.plus(taskDuration).isBefore(second.start))
                return first.end;
        }
        return second == null ? LocalDateTime.now().plus(taskDuration) : second.end;
    }
}
