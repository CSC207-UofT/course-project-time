package datagateway.task;

import datagateway.Observer;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObservableTaskEntityManager implements ObservableTaskRepository {

    private final TodoListManager taskManager;

    private final List<Observer<TaskReader>> onCreationObservers = new ArrayList<>();
    private final List<Observer<TaskReader>> onUpdateObservers = new ArrayList<>();


    public ObservableTaskEntityManager(TodoListManager taskManager) {
        this.taskManager = taskManager;
    }


    @Override
    public void addCreationObserver(Observer<TaskReader> observer) {
        onCreationObservers.add(observer);
    }

    @Override
    public void addUpdateObserver(Observer<TaskReader> observer) {
        onUpdateObservers.add(observer);
    }

    private void notifyCreationObservers(TaskReader tr) {
        onCreationObservers.forEach(o -> o.notifyObserver(tr));
    }

    private void notifyUpdateObservers(TaskReader tr) {
        onUpdateObservers.forEach(o -> o.notifyObserver(tr));
    }

    @Override
    public long addTask(String name, Duration duration, LocalDateTime deadline, List<String> subtasks) {
        long newTaskId = taskManager.addTask(name, duration, deadline, subtasks);
        TaskReader newTask = getTask(newTaskId);
        notifyCreationObservers(newTask);
        return newTaskId;
    }

    @Override
    public TaskReader getTask(long taskId) {
        return taskManager.getTask(taskId);
    }

    @Override
    public Map<Long, List<TaskReader>> getAllTasks() {
        return taskManager.getAllTasks();
    }

    @Override
    public void completeTask(long taskId) {
        taskManager.completeTask(taskId);
        notifyUpdateObservers(getTask(taskId));
    }

    @Override
    public void updateName(long id, String newName) {
        taskManager.updateName(id, newName);
        notifyUpdateObservers(getTask(id));
    }

    @Override
    public void updateDuration(long id, Duration newDuration) {
        taskManager.updateDuration(id, newDuration);
        notifyUpdateObservers(getTask(id));
    }

    @Override
    public void updateDeadline(long id, LocalDateTime newDeadline) {
        taskManager.updateDeadline(id, newDeadline);
        notifyUpdateObservers(getTask(id));
    }

    @Override
    public void addSubtask(long id, String subtask) {
        taskManager.addSubtask(id, subtask);
        notifyUpdateObservers(getTask(id));
    }

    @Override
    public void removeSubtask(long id, String subtask) {
        taskManager.removeSubtask(id, subtask);
        notifyUpdateObservers(getTask(id));
    }

    @Override
    public void loadTodo(String filepath) throws IOException {
        taskManager.loadTodo(filepath);
    }

    @Override
    public void saveTodo(String filepath) throws IOException {
        taskManager.saveTodo(filepath);
    }
}
