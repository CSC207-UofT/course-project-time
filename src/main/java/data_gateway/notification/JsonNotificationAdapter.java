package data_gateway.notification;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.Notification;

import java.io.IOException;

// Based on GSON type adapter documentation from
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html
public class JsonNotificationAdapter extends TypeAdapter<Notification> {

    @Override
    public void write(JsonWriter jsonWriter, Notification notification) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.setIndent("    ");
        jsonWriter.name("associatedId").value(notification.getAssociatedId());
        jsonWriter.name("notificationDateTime").value(notification.getNotificationDateTime().toString());
        jsonWriter.name("timeInAdvance").value(notification.getNotificationTimeInAdvance().toString());
        jsonWriter.name("message").value(notification.getMessage());
        jsonWriter.endObject();
    }


    @Override
    public Notification read(JsonReader jsonReader) {
        return null;
    }
}
