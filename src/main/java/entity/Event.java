package main.java.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An Event stores the name of the event, the start time
 * and end time of the event, tags, the corresponding task,
 * and the dates of event.
 */
public class Event {

    private  int id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Set<String> tags;
    private Task task;
    private Set<LocalDate> dates;

    /**
     * Construct an event based on a task.
     * @param task the main task that will be changed into an event
     * @param startTime the start time of the event
     * @param endTime the end time the event
     */
    public Event(Task task, LocalDateTime startTime, LocalTime endTime) {
        this.task = task;
        this.startTime = startTime.toLocalTime();
        this.endTime = endTime;
        this.tags = new HashSet<String>();
        this.dates = new HashSet<LocalDate>();
        this.dates.add(startTime.toLocalDate());
    }

    /**
     * Construct an event directly, with the event name, start time,
     * end time, and the date
     * @param eventName the name of the event
     * @param startTime the start time of the event (without date)
     * @param endTime the end time of the event (without date)
     * @param tags the tags of the event
     * @param date the date time of the event
     */
    public Event(String eventName, LocalTime startTime, LocalTime endTime,
                 Set<String> tags, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        Duration timeNeeded = Duration.between(startTime, endTime);
        this.task = new Task(eventName, timeNeeded);
        this.dates = new HashSet<LocalDate>();
        this.dates.add(date);
    }

    public void setEventName(String newName) {
        this.task.setTaskName(newName);
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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

    /**
     * Add a repeated date to dates
     * @param date the repeated date
     */
    public void addRepeatedDate(LocalDate date) {
        this.dates.add(date);
    }

    /**
     * Add a list of repeated dates to dates
     * @param dates list of repeated dates
     */
    public void addRepeatedDates(List<LocalDate> dates) {
        this.dates.addAll(dates);
    }

    public String getEventName() {
        return this.task.getTaskName();
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public Set<String> getTags() {
        return this.tags;
    }

    public Task getTask() {
        return this.task;
    }

    public Set<LocalDate> getDates() {
        return this.dates;
    }

}
