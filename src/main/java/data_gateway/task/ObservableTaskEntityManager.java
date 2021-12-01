package data_gateway.task;

import data_gateway.ObservableProperty;
import data_gateway.PropertyObserver;
import services.task_creation.TodoListTaskCreationModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ObservableTaskEntityManager implements ObservableTaskManager {
    private final TodoListManager taskManager;
    private final ObservableProperty<TaskReader, Long> newTaskObservers;
    private final ObservableProperty<TaskReader, Boolean> updateCompletionObservers;

    public ObservableTaskEntityManager(TodoListManager taskManager) {
        this.taskManager = taskManager;
        newTaskObservers = new ObservableProperty<>();
        updateCompletionObservers = new ObservableProperty<>();
    }
    @Override
    public void addOnCreationObserver(PropertyObserver<TaskReader, Long> observer) {
        newTaskObservers.addObserver(observer);
    }

    @Override
    public void addOnCompletionUpdateObserver(PropertyObserver<TaskReader, Boolean> observer) {
        updateCompletionObservers.addObserver(observer);
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        long newTaskId = taskManager.addTask(taskData);
        TaskReader newTask = getTask(newTaskId);
        newTaskObservers.notifyObservers(newTask, newTaskId);
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
        TaskReader updatedTask = getTask(taskId);
        updateCompletionObservers.notifyObservers(updatedTask, true);
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
