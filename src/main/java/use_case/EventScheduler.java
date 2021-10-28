package main.java.use_case;

import main.java.entity.Event;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventReader;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EventScheduler {
    private ArrayList<Event> eventList;
//    private TaskToEventAuto converter;
    private GapFinder gapFinder;
    private CalendarManager calendarManager;

    public EventScheduler(CalendarManager calendarManager){
//        this.converter = obj;
        this.gapFinder = new SortAndSearch();
        this.calendarManager = calendarManager;
    }


    /**
     * @return false if the event has conflict with the calendar;
     *         return true if the event is added successfully
     */
    public boolean isAvailable(LocalTime startTime, Duration timeNeeded, LocalDate date) {
        // check whether the event has conflict with the calendar
        LocalDateTime targetTime = LocalDateTime.of(date, startTime);
        return this.checkAvailability(targetTime, timeNeeded);
            // the event has conflict with the calendar
    }

    public boolean isAvailableRepeated(LocalTime startTime, Duration timeNeeded, Set<LocalDate> dates){
        // check whether the event has conflict with the calendar
        for (LocalDate date : dates) {
            LocalDateTime targetTime = LocalDateTime.of(date, startTime);
            if (!this.checkAvailability(targetTime, timeNeeded)) {
                // the event has conflict with the calendar
                return false;
            }
        }

        return true;
    }

    /**
     * Finds a gap of time for a task with the given duration.
     * The search heuristic is defined by the GapFinder when constructed.
     *
     * @param timesToIgnore times to ignore even if they are valid time slots.
     * @param taskDuration  the amount of available time to look for.
     *
     * @return a time available in the calendar for at least the given duration
     */
    public LocalDateTime getAvailableTime(List<LocalDateTime> timesToIgnore, Duration taskDuration) {
        List<TimeFrame> timeFramesToIgnore = new ArrayList<>();

        for (LocalDateTime time : timesToIgnore)
            timeFramesToIgnore.add(new TimeFrame(time, time.plus(taskDuration)));
        for (EventReader evt : calendarManager.getAllEvents()) {
            for (LocalDate date : evt.getDates()) {
                LocalDateTime startTime = evt.getStartTime().atDate(date);
                LocalDateTime endTime = evt.getEndTime().atDate(date);
                timeFramesToIgnore.add(new TimeFrame(startTime, endTime));
            }
        }

        return gapFinder.findTimeGap(timeFramesToIgnore, taskDuration);
    }

    /**
     * @param targetTime    the time to check availability for
     * @param timeNeeded    the amount of time to check availability for
     *
     * @return whether the targetTime overlaps with any of the current events.
     */
    public boolean checkAvailability(LocalDateTime targetTime, Duration timeNeeded) {
        for (EventReader evt : calendarManager.getAllEvents()) {
            for (LocalDate date : evt.getDates()) {
                LocalDateTime startTime = evt.getStartTime().atDate(date);
                LocalDateTime endTime = evt.getEndTime().atDate(date);
                if (targetTime.isAfter(startTime) && targetTime.isBefore(endTime)) {
                    return false;
                } else if (targetTime.plus(timeNeeded).isAfter(startTime) && targetTime.plus(timeNeeded).isBefore(endTime)) {
                    return false;
                } else if (targetTime.plus(timeNeeded.dividedBy(2)).isAfter(startTime) && targetTime.plus(timeNeeded.dividedBy(2)).isBefore(endTime)) {
                    // deals with the edge case where the new event completely overlaps a current event
                    return false;
                } else if (startTime.isAfter(targetTime) && startTime.isBefore(targetTime.plus(timeNeeded))) {
                    return false;
                } else if (endTime.isAfter(targetTime) && endTime.isBefore(targetTime.plus(timeNeeded))) {
                    return false;
                }
            }
        }
        return true;
    }
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
    LocalDateTime findTimeGap(List<TimeFrame> timeFramesToIgnore, Duration taskDuration);
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
    @Override
    public LocalDateTime findTimeGap(List<TimeFrame> timeFramesToIgnore, Duration taskDuration) {
        Collections.sort(timeFramesToIgnore);
        TimeFrame first, second = null;
        for (TimeFrame t : timeFramesToIgnore) {
            first = second;
            second = t;
            if (first != null && first.end.plus(taskDuration).isBefore(second.start)) return first.end;
        }
        return second == null ? LocalDateTime.now().plus(taskDuration) : second.end;
    }
}

