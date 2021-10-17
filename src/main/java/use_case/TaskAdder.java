package main.java.use_case;

import main.java.entity.Task;
import main.java.use_case.AccessTodoData;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskAdder {


    private AccessTodoData todoData;
    /**
     * Create a new task without deadline and add it to the todoList
     * @param todoData an instance of a todolist accessor
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     */
    public void addTaskWithoutDeadline(String taskName, Duration timeNeeded,
                                       AccessTodoData todoData) {
        Task task = new Task(taskName, timeNeeded);
        todoData.getTodoList().addTask(task);
    }

    /**
     * Create a new task with deadline and add it to the todoList
     * @param todoData an instance of a todolist accessor
     * @param taskName name of the task
     * @param timeNeeded time needed to complete the task
     * @param deadline deadline of the task
     */
    public void addTaskWithDeadline(String taskName, Duration timeNeeded,
                                    LocalDateTime deadline, AccessTodoData todoData) {
        Task task = new Task(taskName, timeNeeded, deadline);
        todoData.getTodoList().addTask(task);
    }
}
