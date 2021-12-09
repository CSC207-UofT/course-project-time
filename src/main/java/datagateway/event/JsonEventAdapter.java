package datagateway.event;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.Event;
import entity.dates.DateStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

// Based on GSON type adapter documentation from
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html
public class JsonEventAdapter extends TypeAdapter<Event> {

    @Override
    public void write(JsonWriter jsonWriter, Event event) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.setIndent("    ");
        jsonWriter.name("id").value(event.getId());
        jsonWriter.name("tags");
        jsonWriter.beginArray();
        for (String tag : event.getTags()) {
            jsonWriter.value(tag);
        }
        jsonWriter.endArray();

        jsonWriter.name("taskId");
        jsonWriter.value(event.getTaskId());

        jsonWriter.name("strategy");

        jsonWriter.value(serializeStrategy(event.getDateStrategy()));

        jsonWriter.endObject();
    }

    @Override
    public Event read(JsonReader jsonReader) throws IOException {
        long id = 0;
        Set<String> tags = new HashSet<>();
        long taskId = -1;
        DateStrategy strategy = null;

        int read_so_far = 0;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String tagName = jsonReader.nextName();

            switch (tagName) {
                case "id":
                    id = jsonReader.nextLong();
                    read_so_far += 1;
                    break;
                case "tags":
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        tags.add(jsonReader.nextString());
                    }
                    jsonReader.endArray();
                    break;
                case "taskId":
                    taskId = Long.parseLong(jsonReader.nextString());
                    break;
                case "strategy":
                    try {
                        strategy = deserializeStrategy(jsonReader.nextString());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        }
        jsonReader.endObject();

        return new Event(id, taskId, strategy, tags);
    }

    private String serializeStrategy(DateStrategy strategy) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream serializer = new ObjectOutputStream(outputStream);
        serializer.writeObject(strategy);
        serializer.close();
        return outputStream.toString();
    }

    private DateStrategy deserializeStrategy(String strategySerialization) throws IOException, ClassNotFoundException {
        byte[] strategyBytes = strategySerialization.getBytes();
        ObjectInputStream serializationReader = new ObjectInputStream(new ByteArrayInputStream(strategyBytes));
        DateStrategy strategy = (DateStrategy) serializationReader.readObject();
        serializationReader.close();
        return strategy;
    }

}
