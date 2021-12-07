package gui;

import gui.utility.InstanceMapper;
import gui.utility.NavigationHelper;
import gui.view.MonthlyCalendarController;
import gui.view.TodoListPageController;
import gui.view.WeeklyCalendarController;
import gui.viewmodel.MonthlyCalendarViewModel;
import gui.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.servicesfactory.BasicObservableRepositoryFactory;
import services.servicesfactory.NotificationServiceFactory;
import services.servicesfactory.ObservableRepositoryFactory;
import services.servicesfactory.ServicesFactory;

import java.io.IOException;
import java.util.Objects;

public class GUIDriver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewModelFactory factory = configure();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("/monthlyCalendar.fxml"))); // TODO: change it to main page view
        Parent root = loader.load();
        ((MonthlyCalendarController) loader.getController()).init(factory.getMonthlyCalendarViewModel()); // TODO: change it to main page controller
        primaryStage.setTitle("Project Time");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private ViewModelFactory configure() {

        ObservableRepositoryFactory repositoryFactory = new BasicObservableRepositoryFactory();
        ServicesFactory servicesFactory = new NotificationServiceFactory(repositoryFactory);
        ViewModelFactory factory = new ViewModelFactory(repositoryFactory, servicesFactory);

        try {
            repositoryFactory.makeEventRepository().loadEvents("EventData.json");
            repositoryFactory.makeTaskRepository().loadTodo("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        InstanceMapper instanceMapper = new InstanceMapper();
        instanceMapper.addMapping(MonthlyCalendarController.class, factory.getMonthlyCalendarViewModel());
        instanceMapper.addMapping(WeeklyCalendarController.class, factory.getWeeklyCalendarViewModel());
        instanceMapper.addMapping(TodoListPageController.class, factory.getTodoListPageViewModel());
        NavigationHelper.setInstanceMap(instanceMapper);
    }
}
