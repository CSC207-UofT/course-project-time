package main.java.entity_gateway;

import main.java.entity.Event;
import main.java.use_case.CalendarEventModel;
import main.java.use_case.Snowflake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EventEntityManager implements CalendarManager{
    private final ArrayList<Event> eventList;
    private final Snowflake snowflake;

    public EventEntityManager(Snowflake snowflake){
        this.eventList = new ArrayList<>();
        this.snowflake = snowflake;
    }


    @Override
    public boolean addEvent(CalendarEventModel eventData) {
        String name = eventData.getName();
        LocalDateTime startTime = eventData.getStartTime();
        LocalDateTime endTime = eventData.getEndTime();
        HashSet<String> tags = eventData.getTags();
        LocalDate date = eventData.getDate();

        Event event = new Event(snowflake.nextId(), name, startTime.toLocalTime(), endTime.toLocalTime(), tags, date);
        eventList.add(event);
        return eventList.contains(event);
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
    public boolean rescheduleEvent(Long eventId, LocalDateTime startTime, LocalDateTime endTime) {
        for(Event event: this.eventList){
            if (event.getId() == eventId){
                event.setStartTime(startTime.toLocalTime());
                event.setEndTime(endTime.toLocalTime());
                return true;
            }
        }
        return false;
    }
}
