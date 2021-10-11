package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Event {

    // If we create another class for repeated events, this class
    // will be cleaner. In this class, to store the information of repeat,
    // we separate the time and date, so that <dates> can store the
    // repeated dates.

    // To track whether an event is completed in the time duration (success)
    // or not completed (fail), we may need to add variable of completed.
    // However, since the events may be repeated, we may need to change
    // <dates> into a map, so that it can store the status of the event on
    // each day.

    private String eventName;
    private LocalTime startTime;
    private LocalTime endTime;
    private HashSet<String> tags;
    private ArrayList<Task> subTasks;
    private HashSet<LocalDate> dates;

    /**
     * Construct an event based on a mainTask.
     * Since the mainTask stores the time needed,
     * we only need a startTime to decide the endTime.
     * @param mainTask the main task that will be changed into an event
     * @param startTime the start time of the event
     */
    public Event(MainTask mainTask, LocalDateTime startTime) {
        this.eventName = mainTask.getTaskName();
        this.startTime = startTime.toLocalTime();
        this.endTime = this.startTime.plus(mainTask.getTimeNeeded()); // may exceed 24h
        this.tags = new HashSet<String>();
        this.subTasks = mainTask.getSubTasks();
        this.dates = new HashSet<LocalDate>();
        this.dates.add(startTime.toLocalDate());
    }

    /**
     * Construct an event directly, with the event name, start time,
     * end time, and the date
     * @param eventName the name of the event
     * @param startTime the start time of the event (without date)
     * @param endTime the end time of the event (without date)
     * @param date the date time of the event
     */
    public Event(String eventName, LocalTime startTime, LocalTime endTime,
                 HashSet<String> tags, LocalDate date) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.subTasks = new ArrayList<Task>();
        this.dates = new HashSet<LocalDate>();
        this.dates.add(date);
    }

    public void setEventName(String newName) {
        this.eventName = newName;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public boolean removeTag(String tag) {
        if (this.tags.contains(tag)) {
            this.tags.remove(tag);
            return true;
        } else {
            return false;
        }
    }

    public void addSubTask(Task subTask) {
        this.subTasks.add(subTask);
    }

    public boolean removeSubTask(Task subTask) {
        if (this.subTasks.contains(subTask)) {
            this.subTasks.remove(subTask);
            return true;
        } else {
            return false;
        }
    }

    public void addRepeated(LocalDate date) {
        this.dates.add(date);
    }

    public String getEventName() {
        return this.eventName;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public HashSet<String> getTags() {
        return this.tags;
    }

    public ArrayList<Task> getSubTasks() {
        return this.subTasks;
    }

    public HashSet<LocalDate> getDates() {
        return this.dates;
    }

}
