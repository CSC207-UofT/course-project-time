package gui;

import gui.utility.InstanceMapper;
import gui.utility.NavigationHelper;
import gui.view.MonthlyCalendarController;
import gui.view.TodoListPageController;
import gui.view.WeeklyCalendarController;
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
        configure();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/basicPage.fxml")));
        primaryStage.setTitle("Project Time");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void configure() {

        ObservableRepositoryFactory repositoryFactory = new BasicObservableRepositoryFactory();
        ViewModelFactory factory = new ViewModelFactory(repositoryFactory);
        ServicesFactory servicesFactory = new NotificationServiceFactory(repositoryFactory);

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