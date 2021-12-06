package gui.view_model;

import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.Entry;
import data_gateway.event.EventReader;
import data_gateway.event.ObservableEventRepository;
import gui.utility.EventHelper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class WeeklyCalendarViewModel extends ViewModel {

    private final ObservableEventRepository repository;

    private final ObservableList<Entry<String>> entryList;

    private final Map<String, Long> entryToEventIdMapping;

    public WeeklyCalendarViewModel(ObservableEventRepository repository) {
        this.repository = repository;
        repository.addCreationObserver(this::handleCreation);
        repository.addUpdateObserver(this::handleUpdate);

        this.entryList = FXCollections.observableArrayList(new ArrayList<>());
        this.entryToEventIdMapping = new HashMap<>();
        initializeIdMappingAndEntryList(this.repository.getAllEvents());

        this.entryList.addListener((ListChangeListener<Entry<String>>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    Entry<String> entry = c.getAddedSubList().get(0);

                    // if entryList and entryToEventIdMapping has the same size,
                    // entry added to entryList is due to changes in data in the
                    // repository and can be ignored as the mapping has been added.

                    if (entryList.size() == entryToEventIdMapping.size() + 1) {
                        // the added entry is due to a change in the associated view.
                        // need to propagate the change to the repository
                        long eventId = repository.addEvent(entry.getTitle(),
                                entry.getStartAsLocalDateTime(),
                                entry.getEndAsLocalDateTime(),
                                new HashSet<>(),
                                entry.getStartDate());
                        entryToEventIdMapping.replace(entry.getId(), eventId);

                    } else if (entryList.size() != entryToEventIdMapping.size()) {
                        System.err.println("Event data may not be synchronized");
                    }
                } else if (c.wasRemoved()) {
                    for (Entry<String> entry : c.getAddedSubList()) {
                        // TODO: implement event deletion
                        System.out.println("delete function not implemented");
                    }
                }
            }
        });
    }

    private void initializeIdMappingAndEntryList(List<EventReader> readerList) {
        for (EventReader eventReader : readerList) {
            Entry<String> entry = EventHelper.eventReaderToEntry(eventReader);
            entryToEventIdMapping.put(entry.getId(), eventReader.getId());
            entryList.add(entry);
        }
    }

    public ObservableList<Entry<String>> getEntryList() {
        return this.entryList;
    }

    /**
     * Creates a new entry that corresponds to the new event and
     * keeps track of it in entryList.
     * Called by the observable repository.
     * @param eventReader data of new event created
     */
    public void handleCreation(EventReader eventReader) {
        if (entryList.size() == entryToEventIdMapping.size()) {
            // event creation was due to other means
            Entry<String> newEntry = EventHelper.eventReaderToEntry(eventReader);
            entryToEventIdMapping.put(newEntry.getId(), eventReader.getId());
            this.entryList.add(newEntry);
        } else if (entryList.size() != entryToEventIdMapping.size() + 1) {
            System.err.println("Event data may not be synchronized");
        }
    }

    /**
     * Updates an existing entry in the entryList that corresponds to the given event reader.
     * Called by the observable repository.
     * @param eventReader data of event to be updated
     */
    public void handleUpdate(EventReader eventReader) {

    }

    public void updateEventFromView(CalendarEvent event) {

    }

    public void addEventFromView(Entry<String> newEntry) {
        this.entryList.add(newEntry);
    }
}
