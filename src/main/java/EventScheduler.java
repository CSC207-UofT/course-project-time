package main.java;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventScheduler {
    private ArrayList<Event> eventList;
    private TaskToEvent converter;
    private GapFinder gapFinder;

    public EventScheduler(TaskToEvent obj, GapFinder gapFinder){
        this.eventList = new ArrayList<>();
        this.converter = obj;
        this.gapFinder = gapFinder;
    }


    /**
     * @param event the event that will be added
     * @return false if the event has conflict with the calendar;
     *         return true if the event is added successfully
     */
    public boolean addEvent(Event event){
        LocalTime startTime = event.getStartTime();
        Calendar calendar = this.getCalendar();
        Duration timeNeeded = event.getTask().getTimeNeeded();
        // check whether the event has conflict with the calendar
        for (LocalDate date : event.getDates()) {
            LocalDateTime targetTime = LocalDateTime.of(date, startTime);
            if (!this.checkAvailability(targetTime, calendar, timeNeeded)) {
                // the event has conflict with the calendar
                return false;
            }
        }
        // the event has no conflict with the calendar
        // add the event to the eventList and return true
        this.eventList.add(event);
        return true;
    }

    /**
     *
     * @param event event to be removed
     * @return if the event has been removed
     */
    public boolean removeEvent(Event event){
        if (this.eventList.contains(event)) {
            this.eventList.remove(event);
            return true;
        }
        return false;
    }

    /**
     * Converts the uncompleted tasks in a todoList into events and adds them to the eventList if the task has not
     * already been added
     * @param todoList  the todoList of tasks to be converted
     */
    public void uncompletedTasksToEvents(TodoList todoList){
        List<Task> alreadyConvertedTasks = new ArrayList<>() {};
        for (Event event : eventList) {
            alreadyConvertedTasks.add(event.getTask());
        }
        for(Task task: todoList.getUncompletedList()) {
            if (!alreadyConvertedTasks.contains(task)) {
                eventList.add(converter.createEventFromTask(task, getCalendar(), this));
            }
        }
    }

    /**
     *
     * @return a calendar with the current list of events
     */
    public Calendar getCalendar(){
        Event[] calendarEvent = new Event[this.eventList.size()];
        for(int i = 0; i < calendarEvent.length; i++){
            calendarEvent[i] = this.eventList.get(i);
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
     * @param timeNeeded    the amount of time to check availability for
     *
     * @return whether the targetTime overlaps with any of the current events.
     */
    public boolean checkAvailability(LocalDateTime targetTime, Calendar calendar, Duration timeNeeded) {
        for (Event evt : calendar.getEvents()) {
            for (LocalDate date : evt.getDates()) {
                LocalDateTime startTime = evt.getStartTime().atDate(date);
                LocalDateTime endTime = evt.getEndTime().atDate(date);
                if (targetTime.isAfter(startTime) && targetTime.isBefore(endTime)) {
                    return false;
                }
                else if (targetTime.plus(timeNeeded).isAfter(startTime)) {
                    return false;
                }
            }
        }
        return true;
    }
}
