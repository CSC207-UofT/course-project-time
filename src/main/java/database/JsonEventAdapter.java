package database;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

// Based on GSON type adapter documentation from
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html
public class JsonEventAdapter extends TypeAdapter<EventDataClass> {

    private final JsonTaskAdapter jsonTaskAdapter = new JsonTaskAdapter();

    @Override
    public void write(JsonWriter jsonWriter, EventDataClass EventDataClass) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.setIndent("    ");
        jsonWriter.name("id").value(EventDataClass.getId());
        jsonWriter.name("startTime").value(EventDataClass.getStartTime().toString());
        jsonWriter.name("endTime").value(EventDataClass.getEndTime().toString());
        jsonWriter.name("tags");
        jsonWriter.beginArray();
        for (String tag : EventDataClass.getTags()) {
            jsonWriter.value(tag);
        }
        jsonWriter.endArray();

        jsonWriter.name("task");
        jsonTaskAdapter.write(jsonWriter, EventDataClass.getTask());

        jsonWriter.name("dates");
        jsonWriter.beginArray();
        if (EventDataClass.getDates() != null) {
            for (LocalDate date : EventDataClass.getDates()) {
                jsonWriter.value(date.toString());
            }
        } else {
            jsonWriter.nullValue();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public EventDataClass read(JsonReader jsonReader) throws IOException {
        long id = 0;
        LocalTime startTime = null;
        LocalTime endTime = null;
        Set<String> tags = new HashSet<>();
        TaskDataClass task = null;
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
            EventDataClass EventDataClass = new EventDataClass(id, task, startTime, endTime, dates);
            for (String tag : tags) {
                EventDataClass.addTag(tag);
            }

            return EventDataClass;
        }
        return null;
    }
}
