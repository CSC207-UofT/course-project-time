package main.java.entity_gateway;

import main.java.entity.Event;
import main.java.use_case.CalendarEventModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // added this method during notification system code implementation due to convenience
    @Override
    public EventToEventReader getEvent(int id) {
        for(Event event: eventList){
            if (event.getId() == id) {
                return new EventToEventReader(event);
            }
        }
        return null;
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
}
