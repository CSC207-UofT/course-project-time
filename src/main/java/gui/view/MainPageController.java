package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view_model.MainPageViewModel;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, ViewModelBindingController{
    final double labelFontSize = 15;
    private MainPageViewModel viewModel;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private JFXListView<HBox> eventList;

    @FXML
    private JFXListView<HBox> taskList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);

    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (MainPageViewModel) viewModel;

        // event example 1
        Label eventName1 = new Label("Dinner");
        Label eventTime1 = new Label("Dec 6, 2021, 8:00 PM - 9:00 PM");
        eventName1.setFont(new Font(labelFontSize));
        eventTime1.setFont(new Font(labelFontSize));

        eventName1.setMinWidth(550);
        eventTime1.setMaxWidth(550);
        HBox event1 = new HBox(eventName1, eventTime1);
        eventList.getItems().add(event1);

        // event example 2
        Label eventName2 = new Label("Breakfast");
        Label eventTime2 = new Label("Dec 7, 2021,  7:30 AM - 8:00 AM");
        eventName2.setFont(new Font(labelFontSize));
        eventTime2.setFont(new Font(labelFontSize));

        eventName2.setMinWidth(550);
        eventTime2.setMaxWidth(550);
        HBox event2 = new HBox(eventName2, eventTime2);
        eventList.getItems().add(event2);

        // task example 1
        Label taskName1 = new Label("Project Phase 2");
        Label deadLine1 = new Label("Dec 8, 2021, 11:59 PM");
        taskName1.setFont(new Font(labelFontSize));
        deadLine1.setFont(new Font(labelFontSize));

        taskName1.setMinWidth(550);
        taskName1.setMaxWidth(550);
        HBox task1 = new HBox(taskName1, deadLine1);
        taskList.getItems().add(task1);

        // task example 2
        Label taskName2 = new Label("Sleeping");
        Label deadLine2 = new Label("Dec 20, 2021, 10:00 PM");
        taskName2.setFont(new Font(labelFontSize));
        deadLine2.setFont(new Font(labelFontSize));

        taskName2.setMinWidth(550);
        taskName2.setMaxWidth(550);
        HBox task2 = new HBox(taskName2, deadLine2);
        taskList.getItems().add(task2);

        // task example 3
        Label taskName3 = new Label("Sleeping");
        Label deadLine3 = new Label("Dec 20, 2021, 10:00 PM");
        taskName3.setFont(new Font(labelFontSize));
        deadLine3.setFont(new Font(labelFontSize));

        taskName3.setMinWidth(550);
        taskName3.setMaxWidth(550);
        HBox task3 = new HBox(taskName3, deadLine3);
        taskList.getItems().add(task3);

        // task example 4
        Label taskName4 = new Label("Sleeping");
        Label deadLine4 = new Label("Dec 20, 2021, 10:00 PM");
        taskName4.setFont(new Font(labelFontSize));
        deadLine4.setFont(new Font(labelFontSize));

        taskName4.setMinWidth(550);
        taskName4.setMaxWidth(550);
        HBox task4 = new HBox(taskName4, deadLine4);
        taskList.getItems().add(task4);

        // task example 5
        Label taskName5 = new Label("Sleeping");
        Label deadLine5 = new Label("Dec 20, 2021, 10:00 PM");
        taskName5.setFont(new Font(labelFontSize));
        deadLine5.setFont(new Font(labelFontSize));

        taskName5.setMinWidth(550);
        taskName5.setMaxWidth(550);
        HBox task5 = new HBox(taskName5, deadLine5);
        taskList.getItems().add(task5);
    }
}
