package data_gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.Task;
import services.Snowflake;
import services.task_creation.TodoListTaskCreationModel;

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
    int taskCounter;
    private final Snowflake snowflake;

    private final Gson gson;

    public TodoEntityManager(Snowflake snowflake){
        taskCounter = 0;
        this.snowflake = snowflake;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Task.class, new JsonTaskAdapter());
        gson = builder.create();
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        String name = taskData.getName();
        Duration duration = taskData.getDuration();
        LocalDateTime deadline = taskData.getDeadline();
        List<String> subtasks = taskData.getSubtasks();

        taskCounter++;
        Task task = new Task(snowflake.nextId(), name, duration, deadline, subtasks);

        taskArrayList.add(task);
        return task.getId();
    }

    @Override
    public TaskReader getTask(long taskId){
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

    @Override
    public void completeTask(long taskId) {
        for (Task t : taskArrayList)
            if (t.getId() == taskId)
                t.setCompleted(true);
    }


    @Override
    public void updateName(long id, String newName) {
        for (Task task : taskArrayList) {
            if (task.getId() == id){
                task.setTaskName(newName);
            }
        }
    }

    @Override
    public void updateDuration(long id, Duration newDuration) {
        for (Task task : taskArrayList){
            if(task.getId() == id){
                task.setTimeNeeded(newDuration);
            }
        }
    }

    @Override
    public void updateDeadline(long id, LocalDateTime newDeadline) {
        for (Task task : taskArrayList){
            if(task.getId() == id){
                task.setDeadline(newDeadline);
            }
        }
    }


    /**
     * Stores todolist data from an external json file, gson usaged based on
     * code from https://www.baeldung.com/gson-list
     * @param filePath The path of the file containing the todoList
     * @throws FileNotFoundException if the specified file cannot be accessed
     */
    @Override
    public void loadTodo(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            JsonReader reader = new JsonReader(new FileReader(filePath));

            Type listType = new TypeToken<List<Task>>() {}.getType();
            List<Task> tasks = gson.fromJson(reader, listType);

            if(tasks != null)
            {
                this.taskArrayList.addAll(tasks);
            }
            reader.close();
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
        fw.close();
    }
}
