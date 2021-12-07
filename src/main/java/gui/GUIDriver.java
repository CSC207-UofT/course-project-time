package gui;

import datagateway.event.CalendarManager;
import datagateway.event.EventEntityManager;
import datagateway.event.ObservableEventEntityManager;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskEntityManager;
import datagateway.task.ObservableTaskRepository;
import datagateway.task.TodoEntityManager;
import datagateway.task.TodoListManager;
import gui.utility.InstanceMapper;
import gui.utility.NavigationHelper;
import gui.view.AddTaskPageController;
import gui.view.MonthlyCalendarController;
import gui.view.TodoListPageController;
import gui.view.WeeklyCalendarController;
import gui.viewmodel.ViewModelFactory;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/basicPage.fxml")));
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
        instanceMapper.addMapping(AddTaskPageController.class, factory.getAddTaskPageViewModel());
        NavigationHelper.setInstanceMap(instanceMapper);
    }
}
