package main.java.entity_gateway;

import main.java.entity.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskToTaskReader implements TaskReader{
    int id;
    String name;
    Duration duration;
    LocalDateTime deadline;
    List<String> subtasks;
    boolean completed;

    public TaskToTaskReader(int id, String name, Duration duration, LocalDateTime deadline, List<String> subtasks, boolean completed){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.deadline = deadline;
        this.subtasks = subtasks;
        this.completed = completed;
    }

    public TaskToTaskReader(Task task) {
        this(task.getId(), task.getTaskName(), task.getTimeNeeded(), task.getDeadline(), task.getSubTasks(), task.getCompleted());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public List<String> getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean getCompleted() {
        return completed;
    }
}
