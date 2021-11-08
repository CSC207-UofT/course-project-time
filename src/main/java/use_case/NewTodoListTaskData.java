package main.java.use_case;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class NewTodoListTaskData implements TodoListTaskCreationDTO {

    private final int todoListId;
    private final String taskName;
    private final Duration timeNeeded;
    private final LocalDateTime deadline;
    private final List<String> subtasks;

    public NewTodoListTaskData(int todoListId, String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        this.todoListId = todoListId;
        this.taskName = taskName;
        this.timeNeeded = timeNeeded;
        this.deadline = deadline;
        this.subtasks = subTasks;
    }

    @Override
    public int getTodoListId() {
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
