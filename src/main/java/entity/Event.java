package main.java.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An Event stores the unique id, the name of the event, the start time
 * and end time of the event, tags, the associated task,
 * and the dates of event, and the notification times of the event.
 */
public class Event {

    private long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Set<String> tags;
    private Task task;
    private Set<LocalDate> dates;
    private Set<Duration> notificationTimes;

    /**
     * Construct an event based on a task.
     * @param id unique id of event
     * @param task the main task that will be changed into an event
     * @param startTime the start time of the event
     * @param endTime the end time the event
     */
    public Event(long id, Task task, LocalDateTime startTime, LocalTime endTime) {
        this.id = id;
        this.task = task;
        this.startTime = startTime.toLocalTime();
        this.endTime = endTime;
        this.tags = new HashSet<String>();
        this.dates = new HashSet<LocalDate>();
        this.dates.add(startTime.toLocalDate());
        this.notificationTimes = new HashSet<Duration>();
    }

    /**
     * Construct an event directly, with the event name, start time,
     * end time, and the date
     * @param id unique id of event
     * @param eventName the name of the event
     * @param startTime the start time of the event (without date)
     * @param endTime the end time of the event (without date)
     * @param tags the tags of the event
     * @param date the date time of the event
     */
    public Event(long id, String eventName, LocalTime startTime, LocalTime endTime,
                 HashSet<String> tags, LocalDate date) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        Duration timeNeeded = Duration.between(startTime, endTime);
        this.task = new Task(id, eventName, timeNeeded);
        this.dates = new HashSet<LocalDate>();
        this.dates.add(date);
        this.notificationTimes = new HashSet<Duration>();
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
     * Add a duration to notification times.
     * @param durationInAdvance a duration representing how long in advance to send the notification
     */
    public void addNotificationTime(Duration durationInAdvance) {
        this.notificationTimes.add(durationInAdvance);
    }

    public void removeNotificationTime(Duration durationInAdvance) {
        this.notificationTimes.remove(durationInAdvance);
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

    public long getId() {
        return id;
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


    public Set<Duration> getNotificationTimes() {
        return notificationTimes;
    }

}
