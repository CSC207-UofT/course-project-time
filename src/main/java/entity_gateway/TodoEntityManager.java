package main.java.entity_gateway;

import main.java.entity.Task;
import main.java.entity.TodoList;
import main.java.use_case.TodoListInfoFromTaskReaders;
import main.java.use_case.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TodoEntityManager implements TodoListManager{
    private final ArrayList<Task> taskArrayList;
    TodoList todoList;
    int taskCounter;
    int todoCounter;

    public TodoEntityManager(){
        taskArrayList = new ArrayList<>();
        taskCounter = 0;
        todoCounter = 0;
    }

    @Override
    public int addTask(TodoListTaskCreationModel taskData) {
        String name = taskData.getName();
        Duration duration = taskData.getDuration();
        LocalDateTime deadline = taskData.getDeadline();
        List<String> subtasks = taskData.getSubtasks();

        taskCounter++;
        Task task = new Task(name, duration, deadline, subtasks, taskCounter);

        taskArrayList.add(task);
        return taskCounter;
    }

    @Override
    public int createTodoList() {
        todoList = new TodoList();
        todoCounter++;
        return todoCounter;
    }

    @Override
    public TaskReader getTask(int todoListId, int taskId) {
        return null;
    }

    @Override
    public Map<Integer, List<TaskReader>> getAllTasks() {
        return null;
    }
}
