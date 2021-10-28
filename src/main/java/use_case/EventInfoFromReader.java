package main.java.use_case;

import main.java.entity_gateway.EventReader;

public class EventInfoFromReader implements EventInfo {

    private final EventReader eventReader;

    public EventInfoFromReader(EventReader eventReader) {
        this.eventReader = eventReader;
    }

}
