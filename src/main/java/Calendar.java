package main.java;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Immutable Entity storing many events and some methods to query them.
 */
public class Calendar {

    private final String name;
    private final Event[] events;
    private final TodoList todoList;

    public Calendar(String name) {
        this(name, new Event[0]);  // todo this is 0 else there will be null pointer exception
    }

    public Calendar(String name, Event[] events) {
        this.name = name;
        this.events = events;
        this.todoList = new TodoList();

        for(Event event : this.events)
        {
            Task task = new Task(event.getEventName());
            todoList.addTask(task);
        }
    }


    /**
     * @return the name of this calendar. The name has no functional purpose.
     */
    public String getName() {
        return name;
    }

    public Event[] getEvents() { return events.clone(); }

}

interface GapFinder {

    /**
     * Find a gap of time for the given duration.
     *
     * @param timeFramesToIgnore the time frames to ignore when searching, even if they are valid.
     * @param taskDuration the duration of time to search for.
     *
     * @return a time of the given duration that does not overlap any of the times to ignore.
     */
    public LocalDateTime findTimeGap(List<TimeFrame> timeFramesToIgnore, Duration taskDuration);
}

class TimeFrame implements Comparable<TimeFrame> {
    public final LocalDateTime start;
    public final LocalDateTime end;
    public TimeFrame(LocalDateTime start, LocalDateTime end) {
         this.start = start;
         this.end = end;
    }
    @Override
    public int compareTo(TimeFrame o) {
        return this.start.compareTo(o.start);
    }
}

/**
 * Sorts times to search for the earliest available gap.
 * Assumes that times do not overlap.
 */
class SortAndSearch implements GapFinder {
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
