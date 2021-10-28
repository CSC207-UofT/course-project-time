package main.java.entity_gateway;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TasktoTaskReader implements TaskReader{
    int id;
    String name;
    Duration duration;
    LocalDateTime deadline;
    List<String> subtasks;

    public TasktoTaskReader(int id, String name, Duration duration, LocalDateTime deadline, List<String> subtasks){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.deadline = deadline;
        this.subtasks = subtasks;
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
}
