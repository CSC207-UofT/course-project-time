package gui;

import data_gateway.event.CalendarManager;
import data_gateway.event.EventEntityManager;
import data_gateway.event.ObservableEventEntityManager;
import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskEntityManager;
import data_gateway.task.ObservableTaskRepository;
import data_gateway.task.TodoEntityManager;
import data_gateway.task.TodoListManager;
import gui.utility.InstanceMapper;
import gui.utility.NavigationHelper;
import gui.view.MonthlyCalendarController;
import gui.view.TodoListPageController;
import gui.view.WeeklyCalendarController;
import gui.view_model.TodoListPageViewModel;
import gui.view_model.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Snowflake;

import java.io.IOException;
import java.util.Objects;

public class GUIDriver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        configure();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/mainPage.fxml")));
        primaryStage.setTitle("Project Time");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void configure() {
        Snowflake snowflake = new Snowflake(0, 0, 0);

        CalendarManager calendarManager = new EventEntityManager(snowflake);
        TodoListManager todoListManager = new TodoEntityManager(snowflake);
        try {
            calendarManager.loadEvents("EventData.json");
            todoListManager.loadTodo("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableEventRepository eventRepository = new ObservableEventEntityManager(calendarManager);
        ObservableTaskRepository taskRepository = new ObservableTaskEntityManager(todoListManager);
        ViewModelFactory factory = new ViewModelFactory(eventRepository, taskRepository);

        InstanceMapper instanceMapper = new InstanceMapper();
        instanceMapper.addMapping(MonthlyCalendarController.class, factory.getMonthlyCalendarViewModel());
        instanceMapper.addMapping(WeeklyCalendarController.class, factory.getWeeklyCalendarViewModel());
        instanceMapper.addMapping(TodoListPageController.class, factory.getTodoListPageViewModel());
        NavigationHelper.setInstanceMap(instanceMapper);
    }
}
