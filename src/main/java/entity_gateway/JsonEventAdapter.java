package main.java.entity_gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import main.java.entity.Event;
import main.java.entity.Task;
import org.junit.jupiter.api.Tags;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonEventAdapter extends TypeAdapter<Event> {

    private Gson gson;
    public JsonEventAdapter()
    {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Task.class, new JsonTaskAdapter());
        gson = builder.create();
    }

    @Override
    public void write(JsonWriter jsonWriter, Event event) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.setIndent("    ");
        jsonWriter.beginObject();
        jsonWriter.name("id").value(event.getId());
        jsonWriter.name("startTime").value(event.getStartTime().toString());
        jsonWriter.name("endTime").value(event.getEndTime().toString());
        jsonWriter.name("tags");
        jsonWriter.beginArray();
        for(String tag : event.getTags())
        {
            jsonWriter.value(tag);
        }
        jsonWriter.endArray();

        jsonWriter.name("task");
        jsonWriter.value(gson.toJson(event.getTask()));

        jsonWriter.name("dates");
        jsonWriter.beginArray();
        for(LocalDate date : dates) {
            jsonWriter.value(date.toString());
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }
    private long id;
    private LocalDateTime startTime;
    private LocalTime endTime;
    private Set<String> tags;
    private Task task;
    private Set<LocalDate> dates;

    @Override
    public Event read(JsonReader jsonReader) throws IOException {
        long id = 0;
        LocalDateTime startTime = null;
        LocalTime endTime = null;
        Set<String> tags = new HashSet<>();
        Task task = null;
        Set<LocalDate> dates = new HashSet<>();


        int read_so_far = 0;
        jsonReader.beginObject();
        while(jsonReader.hasNext())
        {
            String name = jsonReader.nextName();

            switch(name) {
                case "id":
                    id = jsonReader.nextLong();
                    read_so_far += 1;
                    break;
                case "startTime":
                    read_so_far += 1;
                    startTime = LocalDateTime.parse(jsonReader.nextString());
                    break;
                case "endTime":
                    read_so_far += 1;
                    endTime = LocalTime.parse(jsonReader.nextString());
                    break;
                case "tags":
                    jsonReader.beginArray();
                    while(jsonReader.hasNext())
                    {
                        tags.add(jsonReader.nextString());
                    }
                    jsonReader.endArray();
                    break;
                case "task":
                    task = gson.fromJson(jsonReader.nextString(), Task.class);
                    read_so_far += 1;
                    break;
                case "dates":
                    jsonReader.beginArray();
                    while(jsonReader.hasNext())
                    {
                        dates.add(LocalDate.parse(jsonReader.nextString()));
                    }
                    jsonReader.endArray();
                    break;
            }
        }
        jsonReader.endObject();

        if(read_so_far == 3)
        {
            return new Event(id, task, startTime, endTime );
        }
        return null;
    }
}
