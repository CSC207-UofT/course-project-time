package datagateway.event;

import database.EventDataClass;

import java.util.List;

public interface EventAccessInterface {
    List<EventDataClass> getEventData();
}
