package datagateway.pomodoro;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import entity.PomodoroTimer;

import java.io.IOException;


public class JsonPomodoroAdaptor extends TypeAdapter<PomodoroTimer> {

    @Override
    public void write(JsonWriter jsonWriter, PomodoroTimer pomodoroTimer) throws IOException {
        jsonWriter.setIndent("    ");

        jsonWriter.beginObject();
        jsonWriter.name("startTime").value(pomodoroTimer.getStartTime());
        jsonWriter.name("isWorkTime").value(pomodoroTimer.getIsWork());
        jsonWriter.name("breakDuration").value(pomodoroTimer.getBreakDuration());
        jsonWriter.name("workDuration").value(pomodoroTimer.getWorkDuration());
        jsonWriter.endObject();
    }

    @Override
    public PomodoroTimer read(JsonReader jsonReader) throws IOException {
        long startTime = 0;
        boolean isWork = false;
        long breakDuration = 0;
        long workDuration = 0;

        int read_so_far = 0;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();

            switch (name) {
                case "startTime":
                    startTime = jsonReader.nextLong();
                    read_so_far += 1;
                    break;
                case "taskName":
                    read_so_far += 1;
                    isWork = jsonReader.nextBoolean();
                    break;
                case "breakDuration":
                    read_so_far += 1;
                    breakDuration = jsonReader.nextLong();
                    break;
                case "workDuration":
                    read_so_far += 1;
                    workDuration = jsonReader.nextLong();
                    break;
            }
        }
        jsonReader.endObject();

        int MIN_TASK_ATTRIBUTES = 4;
        if (read_so_far == MIN_TASK_ATTRIBUTES) {
            return new PomodoroTimer(startTime, isWork, breakDuration, workDuration, false);
        }
        return null;

    }
}
