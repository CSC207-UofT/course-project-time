package main.java;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskAdder {

    /**
     * Create a new task without deadline and add it to the todoList
     * @param todoList the todoList that the new task will be added to
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     */
    public void addTaskWithoutDeadline(String taskName, Duration timeNeeded,
                                       TodoList todoList) {
        Task task = new Task(taskName, timeNeeded);
        todoList.addTask(task);
    }

    /**
     * Create a new task with deadline and add it to the todoList
     * @param todoList the todoList that the new task will be added to
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     */
    public void addTaskWithDeadline(String taskName, Duration timeNeeded,
                                    LocalDateTime deadline, TodoList todoList) {
        Task task = new Task(taskName, timeNeeded, deadline);
        todoList.addTask(task);
    }
}
