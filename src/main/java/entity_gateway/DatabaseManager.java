package main.java.entity_gateway;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;
import main.java.entity.TodoList;
import main.java.use_case.CalendarEventData;
import main.java.use_case.CalendarEventModel;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class DatabaseManager {

    private static Gson gson= new Gson();

    public static List<TaskReader> SaveTodo(String filename) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(filename));
        return  gson.fromJson(reader, Calendar.class);

    }

    public static List<TaskReader> AccessTodo(String filename) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(filename));
        Type listType = new TypeToken<List<TaskReader>>(){}.getType();
        return gson.fromJson(reader, listType);

    }
}
