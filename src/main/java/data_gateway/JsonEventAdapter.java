package data_gateway;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.Event;
import entity.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class JsonEventAdapter extends TypeAdapter<Event> {

    private final JsonTaskAdapter jsonTaskAdapter = new JsonTaskAdapter();

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
            String tagName = jsonReader.nextName();

            switch (tagName) {
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

        int MIN_EVENT_ATTRIBUTES = 3;
        if (read_so_far == MIN_EVENT_ATTRIBUTES && task != null) {
            Event event = new Event(id, task, startTime, endTime, dates);
            for (String tag : tags) {
                event.addTag(tag);
            }

            return event;
        }
        return null;
    }
}
