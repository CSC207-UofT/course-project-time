package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * An Event stores the name of the event, the start time
 * and end time of the event, tags, the corresponding task,
 * and the dates of event.
 */
public class Event {

    private final long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private final Set<String> tags;
    private final long taskId;
    private final Set<LocalDate> dates;

    /**
     * Construct an event based on a task.
     * @param id unique id of event
     * @param startTime the start time of the event
     * @param endTime the end time the event
     */
    public Event(long id, long taskId, LocalDateTime startTime, LocalTime endTime) {
        this.id = id;
        this.taskId = taskId;
        this.startTime = startTime.toLocalTime();
        this.endTime = endTime;
        this.tags = new HashSet<>();
        this.dates = new HashSet<>();
        this.dates.add(startTime.toLocalDate());
    }

    /**
     * Construct an event based on a task.
     * @param id unique id of event
     * @param startTime the start time of the event
     * @param endTime the end time the event
     */
    public Event(long id, long taskId, LocalTime startTime, LocalTime endTime,
                 Set<LocalDate> dates) {
        this.id = id;
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = new HashSet<>();
        this.dates = dates;
    }

    /**
     * Construct an event directly, with the event name, start time,
     * end time, and the date
     * @param id unique id of event
     * @param startTime the start time of the event (without date)
     * @param endTime the end time of the event (without date)
     * @param tags the tags of the event
     * @param date the date time of the event
     */
    public Event(long id, LocalTime startTime, LocalTime endTime,
                 Set<String> tags, LocalDate date, long taskId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.taskId = taskId;
        this.dates = new HashSet<>();
        this.dates.add(date);
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

    public void removeTag(String tag){this.tags.remove(tag);}

    public long getId() {
        return id;
    }

    public long getTaskId() {
        return taskId;
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

    public Set<LocalDate> getDates() {
        return this.dates;
    }
}
