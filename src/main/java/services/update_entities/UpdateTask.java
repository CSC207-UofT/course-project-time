package services.update_entities;

import data_gateway.TodoEntityManager;
import data_gateway.TodoListManager;


import java.time.Duration;
import java.time.LocalDateTime;


public class UpdateTask {
    TodoListManager todoListManager;
    long id;

    public UpdateTask(TodoEntityManager todoEntityManager, long id){
        this.todoListManager = todoEntityManager;
        this.id = id;
    }

    public void updateName(String newName){
        todoListManager.updateName(id, newName);
    }

    public void updateDuration(Duration newDuration){
        todoListManager.updateDuration(id, newDuration);
    }

    public void updateDeadline(LocalDateTime newDeadline){
        todoListManager.updateDeadline(id, newDeadline);
    }

    public void addSubtasks(String subtask){todoListManager.addSubtask(id, subtask);}

    public void removeSubtasks(String subtask){todoListManager.removeSubtask(id, subtask);}

    public void completeTask() {
        todoListManager.completeTask(id);
    }

}
