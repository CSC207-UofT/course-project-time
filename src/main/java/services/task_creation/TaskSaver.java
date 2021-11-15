package main.java.services.task_creation;

import main.java.data_gateway.TodoListManager;

import java.io.IOException;

public class TaskSaver {

    private final TodoListManager todoListManager;
    public TaskSaver(TodoListManager todoListData)
    {
        todoListManager = todoListData;
    }

    public void save(String filename) throws IOException {
        todoListManager.saveTodo(filename);
    }
}
