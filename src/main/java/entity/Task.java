package main.java.entity;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Task stores the name of the task, whether it is completed,
 * the time needed to complete the task (optional), the deadline
 * of the class (optional), and subtasks (optional)
 *
 * - timeNeeded: the time duration needed to complete the task
 * - notificationTimes: a set of times in Duration presenting how long in advance
 *                      to send the event remainder notification
 */
public class Task {

    private final int id;
    private String taskName;
    private boolean completed;
    private Duration timeNeeded;
    private LocalDateTime deadline;
    private List<String> subTasks;
    private Set<Duration> notificationTimes;

    private static final Duration DEFAULT_DURATION = Duration.ofHours(1);

    /**
     * Construct a task with task name
     * @param taskName name of the task
     */
    public Task(String taskName) {
        this(taskName, DEFAULT_DURATION, null);
    }

    /**
     * Construct a task with task name and time needed
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     */
    public Task(String taskName, Duration timeNeeded) {
        this(taskName, timeNeeded, null, new ArrayList<>(), 0);
    }

    /**
     * Construct a task with task name, time needed, and deadline
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     */
    public Task(String taskName, Duration timeNeeded, LocalDateTime deadline) {
        this(taskName, timeNeeded, deadline, new ArrayList<>(), 0);
    }

    /**
     * Construct a task with task name, time needed, and deadline
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     * @param subTasks list of subtasks
     * @param id differentiate different tasks
     */
    public Task(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks, int id) {
        this.taskName = taskName;
        this.completed = false;
        this.timeNeeded = timeNeeded;
        this.deadline = deadline;
        this.subTasks = new ArrayList<>(subTasks);
        this.id = id;
        this.notificationTimes = new HashSet<Duration>();
    }

    public Task(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        this(taskName, timeNeeded, deadline, subTasks, 0);
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

    public void addNotificationTime(Duration durationInAdvance) {
        this.notificationTimes.add(durationInAdvance);
    }

    public void removeNotificationTime(Duration durationInAdvance) {
        this.notificationTimes.remove(durationInAdvance);
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

    public int getId() {
        return this.id;
    }

    public Set<Duration> getNotificationTimes() {
        return notificationTimes;
    }
}
