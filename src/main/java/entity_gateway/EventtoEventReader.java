package main.java.entity_gateway;

import main.java.entity.Event;

public class EventtoEventReader implements EventReader{
    Event event;

    public EventtoEventReader(Event event){
        this.event = event;
    }
}
