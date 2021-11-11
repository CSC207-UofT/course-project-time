package main.java.use_case;

import main.java.entity.Event;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventEntityManager;
import main.java.entity_gateway.TodoListManager;

import java.io.IOException;

public class TaskSaver {

    private TodoListManager todoListManager;
    public TaskSaver(TodoListManager todoListData)
    {
        todoListManager = todoListData;
    }

    public void save(String filename) throws IOException {
        todoListManager.saveTodo(filename);
    }
}
