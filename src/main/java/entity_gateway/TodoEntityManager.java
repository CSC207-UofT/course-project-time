package main.java.entity_gateway;

import main.java.entity.Task;
import main.java.entity.TodoList;
import main.java.use_case.Snowflake;
import main.java.use_case.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoEntityManager implements TodoListManager{
    private final List<Task> taskArrayList;
    TodoList todoList;
    int taskCounter;
    int todoCounter;
    private final Snowflake snowflake;

    public TodoEntityManager(Snowflake snowflake){
        taskArrayList = new ArrayList<>();
        taskCounter = 0;
        todoCounter = 0;
        this.snowflake = snowflake;
    }

    @Override
    public int addTask(TodoListTaskCreationModel taskData) {
        String name = taskData.getName();
        Duration duration = taskData.getDuration();
        LocalDateTime deadline = taskData.getDeadline();
        List<String> subtasks = taskData.getSubtasks();

        taskCounter++;
        Task task = new Task(snowflake.nextId(), name, duration, deadline, subtasks);

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
    public TaskReader getTask(long todoListId, long taskId){
        for (Task t : taskArrayList)
            if (t.getId() == taskId)
                return new TaskToTaskReader(t);
        return null;
    }

    // added this method during notification system code implementation due to convenience
    @Override
    public TaskReader getTask(long taskId) {
        for (Task t : taskArrayList)
            if (t.getId() == taskId)
                return new TaskToTaskReader(t);
        return null;
    }

    @Override
    public Map<Long, List<TaskReader>> getAllTasks() {
        Map<Long, List<TaskReader>> taskMap = new HashMap<>();
        List<TaskReader> todoListTaskReaders = new ArrayList<>();
        for (Task t : taskArrayList)
            todoListTaskReaders.add(new TaskToTaskReader(t));
        // 0 because there is one todolist
        taskMap.put(0L, todoListTaskReaders);
        return taskMap;
    }

    // added this method during notification system code implementation due to convenience
    @Override
    public List<TaskReader> getAllTasksInList() {
        List<TaskReader> todoListTaskReaders = new ArrayList<>();
        for (Task t : taskArrayList)
            todoListTaskReaders.add(new TaskToTaskReader(t));
        return todoListTaskReaders;
    }
}
