package main.java.entity;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Task stores the name of the task, whether it is completed,
 * the time needed to complete the task (optional), the deadline
 * of the class (optional), and subtasks (optional)
 */
public class Task {

    private long id;
    private String taskName;
    private boolean completed;
    private Duration timeNeeded;
    private LocalDateTime deadline;
    private List<String> subTasks;

    public static final Duration DEFAULT_DURATION = Duration.ofHours(1);

    /**
     * Construct a task with task name
     * @param taskName name of the task
     */
    public Task(long id, String taskName) {
        this(id, taskName, DEFAULT_DURATION, null);
    }

    /**
     * Construct a task with task name and time needed
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     */
    public Task(long id, String taskName, Duration timeNeeded) {
        this(id, taskName, timeNeeded, null, new ArrayList<>());
    }

    /**
     * Construct a task with task name, time needed, and deadline
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     */
    public Task(long id, String taskName, Duration timeNeeded, LocalDateTime deadline) {
        this(id, taskName, timeNeeded, deadline, new ArrayList<>());
    }

    /**
     * Construct a task with task name, time needed, and deadline
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     * @param subTasks list of subtasks
     */
    public Task(long id, String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
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

    public void setSubTasks(List<String> subTasks) {
        this.subTasks = subTasks;
    }

    /**
     * Add a subtask to subTasks.
     *
     * @param subTask the task that will be added
     */
    public void addSubTask(String subTask) {
        this.subTasks.add(subTask);
    }

    /**
     * Remove a subtask from subTasks.
     *
     * @param subTask the task that will be removed
     * @return true iff subTask is in subTask and is removed successfully
     *
     */
    public boolean removeSubTask(String subTask) {
        if (this.subTasks.contains(subTask)) {
            this.subTasks.remove(subTask);
            return true;
        } else {
            return false;
        }
    }

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
