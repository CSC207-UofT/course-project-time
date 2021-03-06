package gui.view.calendar;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.MonthEntryView;
import com.calendarfx.view.MonthView;
import com.calendarfx.view.page.MonthPage;
import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.view.ViewModelBindingController;
import gui.viewmodel.calendar.CalendarViewModel;
import gui.viewmodel.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlyCalendarController implements Initializable, ViewModelBindingController {

    private CalendarViewModel viewModel;

    @FXML
    private MonthPage monthPage;

    private final ObservableList<Entry<String>> entryList = FXCollections.observableArrayList();

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private ComboBox<String> calendarType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);

        calendarType.getItems().addAll("Month", "Week");
    }

    @FXML
    void calendarTypeSelected(ActionEvent event) throws IOException {
        String selected = calendarType.getValue();
        NavigationHelper.switchCalendarPageType(event, selected);
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (CalendarViewModel) viewModel;
        Bindings.bindContentBidirectional(this.entryList, this.viewModel.getEntryList());


        Calendar calendar = new Calendar("all");
        for (Entry<String> entry : this.entryList) {
            entry.setCalendar(calendar);
        }
        CalendarSource source = new CalendarSource();
        source.getCalendars().add(calendar);
        monthPage.getCalendarSources().add(source);

        EventHandler<CalendarEvent> updateEntryHandler = this::handleUpdateEntry;
        calendar.addEventHandler(updateEntryHandler);

        MonthView monthView = this.monthPage.getMonthView();
        monthView.setEntryViewFactory(new EventCreationHandler(this.entryList, this.viewModel));
    }

    private void handleUpdateEntry(CalendarEvent event) {
        this.viewModel.updateEventFromView(event);
    }

    private record EventCreationHandler(
            ObservableList<Entry<String>> entryList, CalendarViewModel viewModel) implements Callback<Entry<?>, MonthEntryView> {

        /**
         * Handles the entry creation by intercepting it and passing
         * it to the view model, then continue with its creation
         * @param param the entry created
         * @return the entry created, specific for the view
         */
        @Override
        public MonthEntryView call(Entry<?> param) {
            List<Integer> ids = new ArrayList<>();
            for (Entry<String> entry : this.entryList) {
                ids.add(Integer.valueOf(entry.getId()));
            }
            if (!ids.contains(Integer.valueOf(param.getId()))) {
                viewModel.addEventFromView((Entry<String>) param);
            }
            return new MonthEntryView(param);
        }
    }
}
