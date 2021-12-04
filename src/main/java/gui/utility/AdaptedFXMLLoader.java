package gui.utility;

import data_gateway.event.CalendarManager;
import data_gateway.event.EventEntityManager;
import gui.model.EventModelManager;
import gui.view.BasicPageController;
import gui.view.MonthlyCalendarController;
import gui.view.ViewModelBindingController;
import gui.view.WeeklyCalendarController;
import gui.view_model.ViewModel;
import gui.view_model.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import services.Snowflake;

import java.io.IOException;

public class AdaptedFXMLLoader extends FXMLLoader {
    private final ViewModelFactory viewModelFactory;

    public AdaptedFXMLLoader() {
        super();

        // TODO: model manager will be changed to observable repos
        Snowflake snowflake = new Snowflake(0, 0, 0);
        CalendarManager calendarManager = new EventEntityManager(snowflake);
        try {
            calendarManager.loadEvents("EventData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventModelManager manager = new EventModelManager(calendarManager);
        this.viewModelFactory = new ViewModelFactory(manager);
    }

    @Override
    public <Parent> Parent load() throws IOException {
        Parent root = super.load();
        ViewModel viewModel = null;
        ViewModelBindingController controller = this.getController();
        if (controller instanceof MonthlyCalendarController) {
            viewModel = viewModelFactory.getMonthlyCalendarViewModel();
        } else if (controller instanceof WeeklyCalendarController) {
            viewModel = viewModelFactory.getWeeklyCalendarViewModel();
        }

        if (viewModel != null) {
            controller.init(viewModel);
        } else {
            System.err.println("Error in injecting view model to a javafx controller");
        }

        return root;
    }
}
