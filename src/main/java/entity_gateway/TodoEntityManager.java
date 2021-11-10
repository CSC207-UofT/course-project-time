package main.java.entity_gateway;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;
import main.java.entity.TodoList;
import main.java.use_case.TodoListTaskCreationModel;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoEntityManager implements TodoListManager{
    private final List<Task> taskArrayList= new ArrayList<>();
    TodoList todoList;
    int taskCounter;
    int todoCounter;

    private Gson gson = new Gson();

    public TodoEntityManager() {
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
    public TaskReader getTask(int todoListId, int taskId){
        for (Task t : taskArrayList)
            if (t.getId() == taskId)
                return new TaskToTaskReader(t);
        return null;
    }

    @Override
    public Map<Integer, List<TaskReader>> getAllTasks() {
        Map<Integer, List<TaskReader>> taskMap = new HashMap<>();
        List<TaskReader> todoListTaskReaders = new ArrayList<>();
        for (Task t : taskArrayList)
            todoListTaskReaders.add(new TaskToTaskReader(t));
        // 0 because there is one todolist
        taskMap.put(0, todoListTaskReaders);
        return taskMap;
    }

    @Override
    public void loadTodo(String filePath) throws FileNotFoundException {
        if (new File(filePath).exists()) {
            JsonReader reader = new JsonReader(new FileReader(filePath));

            Type listType = new TypeToken<List<Task>>() {}.getType();
            List<Task> tasks = gson.fromJson(reader, listType);

            this.taskArrayList.addAll(tasks);
        }
    }

    @Override
    public void saveTodo(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        String cal_json = gson.toJson(this.taskArrayList);

        if(cal_json != null)
        {
            fw.write(cal_json);
        }
    }
}
