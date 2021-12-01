package data_gateway.event;

import data_gateway.PropertyObserver;

public interface ObservableEventManager extends CalendarManager {

    void addOnCreationObserver(PropertyObserver<EventReader, Long> observer);
    void addOnCompletionUpdateObserver(PropertyObserver<EventReader, Boolean> observer);
}
