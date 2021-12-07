package datagateway.pomodoro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.PomodoroTimer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;


public class PomodoroManager {
    private PomodoroTimer pomodoroTimer;
    private final Gson gson;

    public PomodoroManager() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(PomodoroTimer.class, new JsonPomodoroAdaptor());
        gson = builder.create();
    }

    public void loadTimer(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.isFile()) {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Type pomodoroType = new TypeToken<PomodoroTimer>(){}.getType();
            pomodoroTimer = gson.fromJson(reader, pomodoroType);

            if (pomodoroTimer == null) {
                pomodoroTimer = new PomodoroTimer(
                        0, false, 0, 0, true);
            }
            reader.close();
        }
    }

    public void saveTimer(String savePath) throws IOException {
        FileWriter fw = new FileWriter("PomodoroData.json");
        String pomodoro_json = gson.toJson(this.pomodoroTimer);
        fw.write(pomodoro_json);
        fw.close();
    }

    public void deleteTimer(String filePath) {
        File file = new File(filePath);
        if(file.isFile()) {
            file.delete();
        }
    }

    public PomodoroTimer getPomodoroTimer(){
        return pomodoroTimer;
    }


}
