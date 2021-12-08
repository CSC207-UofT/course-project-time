package database;

import entity.Task;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class EventDataClass {

    private final long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private final Set<String> tags;
    private final Task task;
    private final Set<LocalDate> dates;
    private boolean completed;

    /**
     * Construct an event based on a task.
     * @param id unique id of event
     * @param task the main task that will be changed into an event
     * @param startTime the start time of the event
     * @param endTime the end time the event
     */
    public EventDataClass(long id, Task task, LocalDateTime startTime, LocalTime endTime) {
        this.id = id;
        this.task = task;
        this.startTime = startTime.toLocalTime();
        this.endTime = endTime;
        this.tags = new HashSet<>();
        this.dates = new HashSet<>();
        this.dates.add(startTime.toLocalDate());
        this.completed = task.getCompleted();
    }

    /**
     * Construct an event based on a task.
     * @param id unique id of event
     * @param task the main task that will be changed into an event
     * @param startTime the start time of the event
     * @param endTime the end time the event
     */
    public EventDataClass(long id, Task task, LocalTime startTime, LocalTime endTime,
                 Set<LocalDate> dates) {
        this.id = id;
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = new HashSet<>();
        this.dates = dates;
        this.completed = task.getCompleted();
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
    public EventDataClass(long id, String eventName, LocalTime startTime, LocalTime endTime,
                 HashSet<String> tags, LocalDate date) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        Duration timeNeeded = Duration.between(startTime, endTime);
        this.task = new Task(id, eventName, timeNeeded);
        this.dates = new HashSet<>();
        this.dates.add(date);
        this.completed = false;
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

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag){this.tags.remove(tag);}

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

    public boolean getCompleted() {
        return this.completed;
    }
}
