package main.java.entity_gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import main.java.entity.Event;
import main.java.entity.Task;
import org.junit.jupiter.api.Tags;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class JsonEventAdapter extends TypeAdapter<Event> {

    private Gson gson;
    private JsonTaskAdapter jsonTaskAdapter = new JsonTaskAdapter();

    public JsonEventAdapter() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Task.class, new JsonTaskAdapter());
        gson = builder.create();
    }

    @Override
    public void write(JsonWriter jsonWriter, Event event) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.setIndent("    ");
        jsonWriter.name("id").value(event.getId());
        jsonWriter.name("startTime").value(event.getStartTime().toString());
        jsonWriter.name("endTime").value(event.getEndTime().toString());
        jsonWriter.name("tags");
        jsonWriter.beginArray();
        for (String tag : event.getTags()) {
            jsonWriter.value(tag);
        }
        jsonWriter.endArray();

        jsonWriter.name("task");
        jsonTaskAdapter.write(jsonWriter, event.getTask());

        jsonWriter.name("dates");
        jsonWriter.beginArray();
        if (event.getDates() != null) {
            for (LocalDate date : event.getDates()) {
                jsonWriter.value(date.toString());
            }
        } else {
            jsonWriter.nullValue();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    private String stringToJsonString(String input) {
        return gson.fromJson(input, JsonObject.class).toString();
    }

    @Override
    public Event read(JsonReader jsonReader) throws IOException {
        long id = 0;
        LocalTime startTime = null;
        LocalTime endTime = null;
        Set<String> tags = new HashSet<>();
        Task task = null;
        Set<LocalDate> dates = new HashSet<>();


        int read_so_far = 0;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();

            switch (name) {
                case "id":
                    id = jsonReader.nextLong();
                    read_so_far += 1;
                    break;
                case "startTime":
                    read_so_far += 1;
                    startTime = LocalTime.parse(jsonReader.nextString());
                    break;
                case "endTime":
                    read_so_far += 1;
                    endTime = LocalTime.parse(jsonReader.nextString());
                    break;
                case "tags":
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        tags.add(jsonReader.nextString());
                    }
                    jsonReader.endArray();
                    break;
                case "task":
                    task = jsonTaskAdapter.read(jsonReader);
                    break;
                case "dates":
                    if (jsonReader.peek() != null) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            dates.add(LocalDate.parse(jsonReader.nextString()));
                        }
                        jsonReader.endArray();
                        break;
                    }
            }
        }
        jsonReader.endObject();

        if (read_so_far == 3) {
            Event event = new Event(id, task, startTime, endTime, dates);
            for (String tag : tags) {
                event.addTag(tag);
            }

            return event;
        }
        return null;
    }
}
