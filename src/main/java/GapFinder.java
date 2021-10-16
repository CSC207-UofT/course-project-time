package main.java;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface GapFinder {

    /**
     * Find a gap of time for the given duration.
     *
     * @param timeFramesToIgnore the time frames to ignore when searching, even if they are valid.
     * @param taskDuration       the duration of time to search for.
     * @return a time of the given duration that does not overlap any of the times to ignore.
     */
     LocalDateTime findTimeGap(List<TimeFrame> timeFramesToIgnore, Duration taskDuration);
}
