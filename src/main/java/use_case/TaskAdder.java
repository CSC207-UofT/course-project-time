package main.java.use_case;

import main.java.entity.Task;
import main.java.use_case.AccessTodoData;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskAdder {
    /**
     * creates a task and adds it to the todolist in the todoData
     * @param taskName      name of the task
     * @param timeNeeded    time needed to complete the task
     * @param deadline      deadline of the task
     * @param subTasks      a list of subtasks for the task
     * @param todoData      an instance of a todolist accessor
     */
    public void addTask(String taskName, Duration timeNeeded,
                        LocalDateTime deadline, List<String> subTasks,
                        AccessTodoData todoData) {
        Task task = new Task(taskName, timeNeeded, deadline, subTasks);
        todoData.getTodoList().addTask(task);
    }

}
