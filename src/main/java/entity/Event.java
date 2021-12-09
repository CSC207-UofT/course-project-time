package entity;

import entity.dates.DateStrategy;

import java.util.HashSet;
import java.util.Set;

/**
 * An Event stores the name of the event, the start time
 * and end time of the event, tags, the corresponding task,
 * and the dates of event.
 */
public class Event {

    private final long id;
    private final Set<String> tags;
    private final long taskId;
    private DateStrategy dateStrategy;

    /**
     * Construct an event based on a task.
     * @param id unique id of event
     * @param taskId the id of the associated task which contains name and duration
     * @param dateStrategy the strategy containing the days which this event will reoccur
     * @param tags the list of tags categorizing this event
     */
    public Event(long id, long taskId, DateStrategy dateStrategy, Set<String> tags) {
        this.id = id;
        this.taskId = taskId;
        this.dateStrategy = dateStrategy;
        this.tags = tags;
    }

    /**
     * {@link #Event(long, long, DateStrategy, Set)}
     */
    public Event(long id, long taskId, DateStrategy dateStrategy) {
        this(id, taskId, dateStrategy, new HashSet<>());
    }

    public void setDateStrategy(DateStrategy strategy) {
        this.dateStrategy = strategy;
    }

    public DateStrategy getDateStrategy() {
        return dateStrategy;
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

    public Set<String> getTags() {
        return this.tags;
    }
}
