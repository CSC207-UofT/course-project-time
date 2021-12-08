package datagateway.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.Event;
import services.Snowflake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class EventEntityManager implements CalendarManager{
    private final ArrayList<Event> eventList;
    private final Gson gson;
    private final Snowflake snowflake;

    public EventEntityManager(Snowflake snowflake){
        this.eventList = new ArrayList<>();
        this.snowflake = snowflake;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Event.class, new JsonEventAdapter());
        gson = builder.create();
    }

    public void saveEvents(String savePath) throws IOException {
        FileWriter fw = new FileWriter("EventData.json");
        String cal_json = gson.toJson(this.eventList);
        fw.write(cal_json);
        fw.close();
    }

    /**
     * Loads event data from specified json file, gson code based on examples from
     * //www.baeldung.com/gson-list
     * @param filePath The location of the json file containing event data
     * @throws FileNotFoundException if the specified file cannot be accessed
     */
    public void loadEvents(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.isFile()) {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Type listType = new TypeToken<List<Event>>(){}.getType();
            List<Event> events = gson.fromJson(reader, listType);

            if(events != null) {
                this.eventList.addAll(events);
            }
            reader.close();
        }
    }

    /**
     * Add a new event to eventList using data from eventData
     * @param eventName     the name of the new event
     * @param startTime     the time the event should start
     * @param endTime       the time the event should end
     * @param tags          the tags associated with the event
     * @param date          the date the event should occur
     */
    @Override
    public long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, HashSet<String> tags,
                         LocalDate date) {
        Event event = new Event(snowflake.nextId(), eventName, startTime.toLocalTime(), endTime.toLocalTime(), tags, date);
        eventList.add(event);
        return event.getId();
    }

    /**
     * Return a list of eventReader
     */
    @Override
    public void markEventAsCompleted(long eventId) {
        getById(eventId).setCompleted(true);
    }

    @Override
    public List<EventReader> getAllEvents() {
        List<EventReader> eventReaderList = new ArrayList<>();

        for(Event event: eventList){
            EventReader eventReader = new EventToEventReader(event);
            eventReaderList.add(eventReader);
        }
        return eventReaderList;
    }

    @Override
    public void updateName(long id, String newName) {
        getById(id).setEventName(newName);
    }

    @Override
    public void updateStartTime(long id, LocalTime newStartTime) {
        getById(id).setStartTime(newStartTime);
    }

    @Override
    public void updateEndTime(long id, LocalTime newEndTime) {
        getById(id).setEndTime(newEndTime);
    }

    @Override
    public void addTag(long id, String tag) {
        getById(id).addTag(tag);
    }

    @Override
    public void removeTag(long id, String tag) {
        getById(id).removeTag(tag);
    }

    private Event getById(long id){
        for(Event event : eventList){
            if(event.getId() == id){
                return event;
            }
        }
        return null;
    }

}