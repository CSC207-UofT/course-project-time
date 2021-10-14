package main.java;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventScheduler {
    private ArrayList<Event> eventlst;
    private TaskToEvent converter;
    private TodoList todolst;
    private GapFinder gapFinder;

    public EventScheduler(TaskToEvent obj, GapFinder gapFinder){
        this.eventlst = new ArrayList<Event>();
        this.converter = obj;
        this.gapFinder = gapFinder;
    }

    public void addEvents(Event event){
        this.eventlst.add(event);
    }

    public void removeEvents(Event event){
        this.eventlst.remove(event);
    }

    public void AddTaskToTodoList(){
        for(Event evt: this.eventlst){
            this.todolst.addTask(evt.getTask());
        }

    }
    public Calendar getCalendar(){
        Event[] calendarEvent = new Event[this.eventlst.size()];
        for(int i = 0; i < calendarEvent.length; i++){
            calendarEvent[i] = this.eventlst.get(i);
        }
        return new Calendar("calendar", calendarEvent);
    }

    /**
     * Finds a gap of time for a task with the given duration.
     * The search heuristic is defined by the GapFinder when constructed.
     *
     * @param timesToIgnore times to ignore even if they are valid time slots.
     * @param taskDuration  the amount of available time to look for.
     * @param calendar      the calendar that has all the previously created events
     *
     * @return a time available in the calendar for at least the given duration
     */
    public LocalDateTime getAvailableTime(List<LocalDateTime> timesToIgnore, Duration taskDuration, Calendar calendar) {
        List<TimeFrame> timeFramesToIgnore = new ArrayList<>();

        for (LocalDateTime time : timesToIgnore)
            timeFramesToIgnore.add(new TimeFrame(time, time.plus(taskDuration)));
        for (Event evt : calendar.getEvents()) {
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
     * @param calendar      the calendar that has all the previously created events
     *
     * @return whether the targetTime overlaps with any of the current events.
     */
    public boolean checkAvailability(LocalDateTime targetTime, Calendar calendar) {
        for (Event evt : calendar.getEvents()) {
            for (LocalDate date : evt.getDates()) {
                LocalDateTime startTime = evt.getStartTime().atDate(date);
                LocalDateTime endTime = evt.getEndTime().atDate(date);
                if (targetTime.isAfter(startTime) && targetTime.isBefore(endTime))
                    return false;
            }
        }
        return true;
    }
}
