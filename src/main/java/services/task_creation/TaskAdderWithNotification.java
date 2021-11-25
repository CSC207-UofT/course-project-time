package services.task_creation;

import data_gateway.TodoListManager;
import services.notification_system.NotificationAdder;

public class TaskAdderWithNotification implements TaskAdding{

    private final TodoListManager todoListManager;
    private final NotificationAdder notificationAdder;

    public TaskAdderWithNotification(TodoListManager todoListManager, NotificationAdder notificationAdder) {
        this.todoListManager = todoListManager;
        this.notificationAdder = notificationAdder;
    }

    @Override
    public void addTask(TodoListTaskCreationModel taskData) {
        long taskId = todoListManager.addTask(taskData);
        notificationAdder.createNotification(taskData, taskId);
    }

    @Override
    public void completeTask(long taskId) {
        todoListManager.completeTask(taskId);
    }
}
