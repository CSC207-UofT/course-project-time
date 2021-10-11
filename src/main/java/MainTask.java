package main.java;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * MainTask is a subclass of Task.
 *
 * A MainTask stores the name of the task, whether it is completed,
 * estimated time needed, deadline (optional), and subtasks (optional)
 *
 */
public class MainTask extends Task implements Main{

    private Duration timeNeeded;
    private LocalDateTime deadline;

    /**
     * Construct a MainTask without deadline
     * @param taskName the name of the task
     */
    public MainTask(String taskName) {
        super(taskName);
        this.deadline = null;
    }

    /**
     * Construct a MainTask with deadline
     * @param taskName the name of the task
     * @param deadline the deadline of the task
     */
    public MainTask(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Duration getTimeNeeded() {
        return this.timeNeeded;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
