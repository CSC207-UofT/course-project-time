package data_gateway.event;

import data_gateway.ObservableRepository;

/**
 * Interface allowing implementors polymorphic access to
 * {@link CalendarManager} and {@link ObservableRepository< EventReader >}
 */
public interface ObservableEventRepository extends ObservableRepository<EventReader>, CalendarManager {
}
