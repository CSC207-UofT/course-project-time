package main.java.use_case;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import main.java.entity.Calendar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CalendarSaver {

    private Gson gson;
    public CalendarSaver() {
        gson = new Gson();
    }

    public void SaveCalendar(Calendar calendar) throws IOException {

        // We could use id's for calendars
        String filename = calendar.getName() + ".json";
        FileWriter fw = new FileWriter(filename);
        String cal_json = gson.toJson(calendar);
        fw.write(cal_json);
    }

    public Calendar AccessCalendar(String filename) throws IOException {
        JsonReader reader = new JsonReader(new FileReader(filename));
        return  gson.fromJson(reader, Calendar.class);

    }
}
