package datagateway.event;

import datagateway.ObservableRepository;

/**
 * Interface allowing implementors polymorphic access to
 * {@link CalendarManager} and {@link ObservableRepository< EventReader >}
 */
public interface ObservableEventRepository extends ObservableRepository<EventReader>, CalendarManager {
}
