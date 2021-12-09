package datagateway.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;
import entity.Event;
import entity.dates.DateStrategy;
import services.Snowflake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class EventEntityManager implements CalendarManager{
    private final ArrayList<Event> eventList;
    private final Gson gson;
    private final Snowflake snowflake;
    private final TodoListManager taskManager;

    public EventEntityManager(Snowflake snowflake, TodoListManager taskManager) {
        this.eventList = new ArrayList<>();
        this.snowflake = snowflake;
        this.taskManager = taskManager;
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


    @Override
    public long addEvent(String eventName, DateStrategy strategy, Duration duration, Set<String> tags) {
        long taskId = taskManager.addTask(eventName, duration, null, new ArrayList<>());
        Event event = new Event(snowflake.nextId(), taskId, strategy, tags);
        return event.getId();
    }


    /**
     * Add a new event using an existing Task's data
     * @param taskId        the associative task's id
     * @param dateStrategy  the strategy used to generate the dates this event will occur
     * @param tags          the tags associated with the event
     * @return              the id of the newly created event
     */
    @Override
    public long addEvent(long taskId, DateStrategy dateStrategy, Set<String> tags) {
        Event event = new Event(snowflake.nextId(), taskId, dateStrategy, tags);
        eventList.add(event);
        return event.getId();
    }

    @Override
    public void deleteEvent(long eventId) {
        eventList.removeIf(e -> e.getId() == eventId);
    }

    /**
     * Return a list of eventReader
     */
    @Override
    public void markEventAsCompleted(long eventId) {
        taskManager.completeTask(getById(eventId).getTaskId());
    }

    @Override
    public List<EventReader> getAllEvents() {
        List<EventReader> eventReaderList = new ArrayList<>();

        for(Event event: eventList){
            TaskReader tr = taskManager.getTask(event.getTaskId());
            EventReader eventReader = new EventToEventReader(event, tr);
            eventReaderList.add(eventReader);
        }
        return eventReaderList;
    }

    @Override
    public void updateName(long id, String newName) {
        taskManager.updateName(getById(id).getTaskId(), newName);
    }

    @Override
    public void updateDateStrategy(long id, DateStrategy strategy) {
        getById(id).setDateStrategy(strategy);
    }

    @Override
    public void updateDuration(long id, Duration duration) {
        taskManager.updateDuration(getById(id).getId(), duration);
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
