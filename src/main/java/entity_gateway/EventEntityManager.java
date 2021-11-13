package main.java.entity_gateway;

import main.java.entity.Event;
import main.java.use_case.CalendarEventModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventEntityManager implements CalendarManager{
    private final ArrayList<Event> eventList;

    public EventEntityManager(){
        this.eventList = new ArrayList<>();
    }


    @Override
    public boolean addEvent(CalendarEventModel eventData) {
        String name = eventData.getName();
        LocalDateTime startTime = eventData.getStartTime();
        LocalDateTime endTime = eventData.getEndTime();
        Set<String> tags = eventData.getTags();
        LocalDate date = eventData.getDate();

        Event event = new Event(name, startTime.toLocalTime(), endTime.toLocalTime(), tags, date);
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
    public boolean update(String eventName, LocalDateTime startTime, LocalDateTime endTime) {
        for(Event event: this.eventList){
            if (event.getEventName().equals(eventName)){
                event.setStartTime(startTime.toLocalTime());
                event.setEndTime(endTime.toLocalTime());
                return true;
            }
        }
        return false;
    }
}
