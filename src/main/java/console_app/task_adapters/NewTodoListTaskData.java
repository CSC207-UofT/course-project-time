package main.java.console_app.task_adapters;

import main.java.services.task_creation.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class NewTodoListTaskData implements TodoListTaskCreationModel {

    private final long todoListId;
    private final String taskName;
    private final Duration timeNeeded;
    private final LocalDateTime deadline;
    private final List<String> subtasks;

    public NewTodoListTaskData(long todoListId, String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        this.todoListId = todoListId;
        this.taskName = taskName;
        this.timeNeeded = timeNeeded;
        this.deadline = deadline;
        this.subtasks = subTasks;
    }

    @Override
    public long getTodoListId() {
        return todoListId;
    }

    @Override
    public String getName() {
        return taskName;
    }

    @Override
    public Duration getDuration() {
        return timeNeeded;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public List<String> getSubtasks() {
        return subtasks;
    }
}
