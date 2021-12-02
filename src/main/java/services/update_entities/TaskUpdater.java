package services.update_entities;

import data_gateway.TodoListManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskUpdater implements UpdateTaskBoundary{
    TodoListManager todoListManager;

    public TaskUpdater(TodoListManager todoEntityManager){
        this.todoListManager = todoEntityManager;
    }

    @Override
    public void updateName(long id, String newName) {
        todoListManager.updateName(id, newName);
    }

    @Override
    public void updateDuration(long id, Duration newDuration) {
        todoListManager.updateDuration(id, newDuration);
    }

    @Override
    public void updateDeadline(long id, LocalDateTime newDeadline) {
        todoListManager.updateDeadline(id, newDeadline);
    }

    @Override
    public void addSubtask(long id, String subtask) {
        todoListManager.addSubtask(id, subtask);
    }

    @Override
    public void removeSubtask(long id, String subtask) {
        todoListManager.removeSubtask(id, subtask);

    }

    @Override
    public void completeTask(long id) {
        todoListManager.completeTask(id);

    }
}