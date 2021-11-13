package main.java.entity_gateway;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Duration;

public class DurationAdapter extends TypeAdapter<Duration> {

    @Override
    public void write(JsonWriter jsonWriter, Duration duration) throws IOException {
        jsonWriter.beginObject();
        long seconds = duration.getSeconds();
        jsonWriter.value(seconds);
    }

    @Override
    public Duration read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.endObject();
        return Duration.parse(jsonReader.nextString());
    }
}
