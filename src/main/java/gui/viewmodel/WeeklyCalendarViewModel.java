package gui.viewmodel;

import com.calendarfx.model.Entry;
import datagateway.event.EventReader;
import datagateway.event.ObservableEventRepository;
import gui.utility.EventHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class WeeklyCalendarViewModel extends ViewModel {

    private final ObservableEventRepository repository;

    private final ObservableList<Entry<String>> entryList;

    public WeeklyCalendarViewModel(ObservableEventRepository repository) {
        this.repository = repository;
        repository.addCreationObserver(this::handleCreation);
        repository.addUpdateObserver(this::handleUpdate);

        List<EventReader> eventReaderList = this.repository.getAllEvents();
        this.entryList = FXCollections.observableArrayList(EventHelper.eventReaderToEntry(eventReaderList));
    }

    public ObservableList<Entry<String>> getEntryList() {
        return this.entryList;
    }

    public void handleCreation(EventReader eventReader) {

    }

    public void handleUpdate(EventReader eventReader) {

    }
}
