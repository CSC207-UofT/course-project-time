package datagateway.task;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.Task;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Based on GSON type adapter documentation from
// https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.1/com/google/gson/TypeAdapter.html
public class JsonTaskAdapter extends TypeAdapter<Task> {

    @Override
    public void write(JsonWriter jsonWriter, Task task) throws IOException {
        jsonWriter.setIndent("    ");
        jsonWriter.beginObject();
        jsonWriter.name("id").value(task.getId());
        jsonWriter.name("taskName").value(task.getTaskName());
        jsonWriter.name("completed").value(task.getCompleted());
        jsonWriter.name("timeNeeded").value(task.getTimeNeeded().toString());
        jsonWriter.name("deadline");

        if (task.getDeadline() == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(task.getDeadline().toString());
        }
        jsonWriter.name("subTasks");
        jsonWriter.beginArray();
        for (String subtask : task.getSubTasks()) {
            jsonWriter.value(subtask);
        }
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public Task read(JsonReader jsonReader) throws IOException {
        long id = 0;
        String taskName = "";
        boolean completed = false;
        Duration timeNeeded = Task.DEFAULT_DURATION;
        LocalDateTime deadline = null;
        List<String> subTasks = new ArrayList<>();

        int read_so_far = 0;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();

            switch (name) {
                case "id":
                    id = jsonReader.nextLong();
                    read_so_far += 1;
                    break;
                case "taskName":
                    read_so_far += 1;
                    taskName = jsonReader.nextString();
                    break;
                case "completed":
                    completed = jsonReader.nextBoolean();
                    break;
                case "timeNeeded":
                    timeNeeded = Duration.parse(jsonReader.nextString());
                    break;
                case "deadline":
                    if (jsonReader.peek() != null) {
                        deadline = LocalDateTime.parse(jsonReader.nextString());
                    }
                    break;
                case "subTasks":
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        subTasks.add(jsonReader.nextString());
                    }
                    jsonReader.endArray();
                    break;
            }
        }
        jsonReader.endObject();

        int MIN_TASK_ATTRIBUTES = 2;
        if (read_so_far == MIN_TASK_ATTRIBUTES) {
            Task task = new Task(id, taskName, timeNeeded, deadline, subTasks);
            task.setCompleted(completed);

            return task;
        }
        return null;

    }
}
