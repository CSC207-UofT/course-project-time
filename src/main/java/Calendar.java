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
    private final GapFinder gapFinder;
    private final TodoList todoList;

    public Calendar(String name) {
        this(name, new Event[100]);
    }

    public Calendar(String name, Event[] events) {
        this(name, events, new SortAndSearch());
    }

    public Calendar(String name, Event[] events, GapFinder gapFinder) {
        this.name = name;
        this.events = events;
        this.gapFinder = gapFinder;
        this.todoList = new TodoList();

        for(Event event : this.events)
        {
            Task task = new Task(event.getEventName());
            todoList.addTask(task);
        }
    }

    /**
     * Finds a gap of time for a task with the given duration.
     * The search heuristic is defined by the GapFinder when constructed.
     *
     * @param timesToIgnore times to ignore even if they are valid time slots.
     * @param taskDuration the amount of available time to look for.
     *
     * @return a time available in the calendar for at least the given duration
     */
    public LocalDateTime getAvailableTime(List<LocalDateTime> timesToIgnore, Duration taskDuration) {
        List<TimeFrame> timeFramesToIgnore = new ArrayList<>();

        for (LocalDateTime time : timesToIgnore)
            timeFramesToIgnore.add(new TimeFrame(time, time.plus(taskDuration)));
        for (Event evt : events) {
            for (LocalDate date : evt.getDates()) {
                LocalDateTime startTime = evt.getStartTime().atDate(date);
                LocalDateTime endTime = evt.getEndTime().atDate(date);
                timeFramesToIgnore.add(new TimeFrame(startTime, endTime));
            }
        }

        return gapFinder.findTimeGap(timeFramesToIgnore, taskDuration);
    }

    /**
     * @param targetTime the time to check availability for
     *
     * @return whether the targetTime overlaps with any of the current events.
     */
    public boolean checkAvailability(LocalDateTime targetTime) {
        for (Event evt : events) {
            for (LocalDate date : evt.getDates()) {
                LocalDateTime startTime = evt.getStartTime().atDate(date);
                LocalDateTime endTime = evt.getEndTime().atDate(date);
                if (targetTime.isAfter(startTime) && targetTime.isBefore(endTime))
                    return false;
            }
        }
        return true;
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
