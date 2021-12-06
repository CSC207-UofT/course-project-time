package gui.view_model;

import com.calendarfx.model.Entry;
import data_gateway.event.EventReader;
import data_gateway.event.ObservableEventRepository;
import gui.utility.EventHelper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;


public class MonthlyCalendarViewModel extends ViewModel {

    private final ObservableEventRepository repository;

    private final ObservableList<Entry<String>> entryList;

    private final Map<String, Long> entryToEventIdMapping;

    public MonthlyCalendarViewModel(ObservableEventRepository repository) {
        this.repository = repository;
        repository.addCreationObserver(this::handleCreation);
        repository.addUpdateObserver(this::handleUpdate);

        this.entryList = FXCollections.observableArrayList(new ArrayList<>());
        this.entryToEventIdMapping = new HashMap<>();
        initializeIdMappingAndEntryList(this.repository.getAllEvents());

        this.entryList.addListener((ListChangeListener<Entry<String>>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    System.out.println("event added! in monthly vm");
                    for (Entry<String> entry : c.getAddedSubList()) {
                        // if entryList and entryToEventIdMapping has the same size,
                        // entry added to entryList is due to changes in data in the
                        // repository and can be ignored.
                        if (entryList.size() == entryToEventIdMapping.size() + 1) {
                            // the added entry is due to a change in the associated view.
                            // need to propagate the change to the repository
                            repository.addEvent(entry.getTitle(),
                                    entry.getStartAsLocalDateTime(),
                                    entry.getEndAsLocalDateTime(),
                                    new HashSet<>(),
                                    entry.getStartDate());

                        } else if (entryList.size() != entryToEventIdMapping.size()) {
                            System.err.println("Event data may not be synchronized");
                        }
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
        if (entryList.size() == entryToEventIdMapping.size() + 1) {
            // event creation is propagated from this view model initially
            // entry is in entry list, but mapping is not added
            for (Entry<String> entry : entryList) {
                if (!entryToEventIdMapping.containsKey(entry.getId())) {
                    entryToEventIdMapping.put(entry.getId(), eventReader.getId());
                    return;
                }
            }
            System.err.println("Event data may not be synchronized");
        } else if (entryList.size() == entryToEventIdMapping.size()) {
            // event creation was due to other means
            Entry<String> newEntry = EventHelper.eventReaderToEntry(eventReader);
            entryToEventIdMapping.put(newEntry.getId(), eventReader.getId());
            this.entryList.add(newEntry);
        } else {
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
}
