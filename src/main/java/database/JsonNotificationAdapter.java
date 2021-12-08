package database;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

// Based on GSON type adapter documentation from
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html
public class JsonNotificationAdapter extends TypeAdapter<NotificationDataClass> {

    @Override
    public void write(JsonWriter jsonWriter, NotificationDataClass notification) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.setIndent("    ");
        jsonWriter.name("associatedId").value(notification.getAssociatedId());
        jsonWriter.name("notificationDateTime").value(notification.getNotificationDateTime().toString());
        jsonWriter.name("timeInAdvance").value(notification.getNotificationTimeInAdvance().toString());
        jsonWriter.name("message").value(notification.getMessage());
        jsonWriter.endObject();
    }


    @Override
    public NotificationDataClass read(JsonReader jsonReader) throws IOException {
        long id = 0;
        Duration timeInAdvance = Duration.ZERO;
        LocalDateTime notifDateTime = null;
        String message = "";

        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            String tagName = jsonReader.nextName();

            switch (tagName) {
                case "associatedId" -> id = jsonReader.nextLong();
                case "notificationDateTime" -> notifDateTime = LocalDateTime.parse(jsonReader.nextString());
                case "timeInAdvance" -> timeInAdvance = Duration.parse(jsonReader.nextString());
                case "message" -> message = jsonReader.nextString();
            }
        }
        jsonReader.endObject();

        if (timeInAdvance != null && notifDateTime != null) {
            return new NotificationDataClass(id, timeInAdvance, notifDateTime, message);
        }

        return null;
    }
}
