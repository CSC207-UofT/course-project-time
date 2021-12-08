package database;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Serialized by the database, identical to the task entity, but decoupled to align with CA
public class TaskDataClass {
    private final long id;
    private String taskName;
    private boolean completed;
    private Duration timeNeeded;
    private LocalDateTime deadline;
    private final List<String> subTasks;

    public static final Duration DEFAULT_DURATION = Duration.ofHours(1);

    /**
     * Construct a task with task name
     * @param taskName name of the task
     */
    public TaskDataClass(long id, String taskName) {
        this(id, taskName, DEFAULT_DURATION, null);
    }

    /**
     * Construct a task with task name and time needed
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     */
    public TaskDataClass(long id, String taskName, Duration timeNeeded) {
        this(id, taskName, timeNeeded, null, new ArrayList<>());
    }

    /**
     * Construct a task with task name, time needed, and deadline
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     */
    public TaskDataClass(long id, String taskName, Duration timeNeeded, LocalDateTime deadline) {
        this(id, taskName, timeNeeded, deadline, new ArrayList<>());
    }

    /**
     * Construct a task with task name, time needed, and deadline
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     * @param subTasks list of subtasks
     */
    public TaskDataClass(long id, String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        this.id = id;
        this.taskName = taskName;
        this.completed = false;
        this.timeNeeded = timeNeeded;
        this.deadline = deadline;
        this.subTasks = new ArrayList<>(subTasks);
    }

    public void setTaskName(String newName) {
        this.taskName = newName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTimeNeeded(Duration timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void addSubtask(String subtask){this.subTasks.add(subtask);}

    public void removeSubtask(String subtask){this.subTasks.remove(subtask);}

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public Duration getTimeNeeded() {
        return this.timeNeeded;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public List<String> getSubTasks() {
        return this.subTasks;
    }
}
