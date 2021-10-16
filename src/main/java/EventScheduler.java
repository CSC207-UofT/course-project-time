package main.java;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventScheduler {
    private ArrayList<Event> eventList;
//    private TaskToEvent converter;
    private GapFinder gapFinder;

    public EventScheduler(GapFinder gapFinder){
//        this.converter = obj;
        this.gapFinder = gapFinder;
    }


    /**
     * @return false if the event has conflict with the calendar;
     *         return true if the event is added successfully
     */
    public boolean isAvailable(LocalTime startTime, Duration timeNeeded, LocalDate date, ManageCalendarData calendarData) {
        // check whether the event has conflict with the calendar
        LocalDateTime targetTime = LocalDateTime.of(date, startTime);
        return this.checkAvailability(targetTime, calendarData.getCalendar(), timeNeeded);
            // the event has conflict with the calendar
    }

    public boolean isAvailableRepeated(LocalTime startTime, Duration timeNeeded, Set<LocalDate> dates, ManageCalendarData calendarData){
        // check whether the event has conflict with the calendar
        for (LocalDate date : dates) {
            LocalDateTime targetTime = LocalDateTime.of(date, startTime);
            if (!this.checkAvailability(targetTime, calendarData.getCalendar(), timeNeeded)) {
                // the event has conflict with the calendar
                return false;
            }
        }

        return true;
    }

    /**
     * Converts the uncompleted tasks in a todoList into events and adds them to the eventList if the task has not
     * already been added
     */
    public void uncompletedTasksToEvents(ManageTodoData todoData, ManageCalendarData calendarData){
        List<Task> alreadyConvertedTasks = new ArrayList<>() {};
        for (Event event : eventList) {
            alreadyConvertedTasks.add(event.getTask());
        }

        for(Task task: todoData.getTodoList().getUncompletedList()) {
            if (!alreadyConvertedTasks.contains(task)) {

                // TODO implement this method, now that event scheduler doesn't store events
//                Event new_event = new Event(task, availableTime, availableTime.toLocalTime().plus(task.getTimeNeeded()));
//                calendarData.addEvent(converter.createEventFromTask(task, calendarData.getCalendar(), this));
            }
        }
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
