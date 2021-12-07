package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view_model.MainPageViewModel;
import gui.view_model.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainPageController implements Initializable, ViewModelBindingController {

    private MainPageViewModel viewModel;
    final double labelFontSize = 15;

    private final ObservableMap<String, String> taskInfoMap = FXCollections.observableHashMap();
    private final ObservableMap<String, String> eventInfoMap = FXCollections.observableHashMap();

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private JFXListView<HBox> eventListView;

    @FXML
    private JFXListView<HBox> taskListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (MainPageViewModel) viewModel;

        Bindings.bindContentBidirectional(this.taskInfoMap, this.viewModel.getTaskInfoMap());

        Bindings.bindContentBidirectional(this.eventInfoMap, this.viewModel.getEventInfoMap());

        for (Map.Entry<String, String> taskInfo : taskInfoMap.entrySet()) {
            Label taskName = new Label(taskInfo.getKey());
            Label deadLine = new Label(taskInfo.getValue());
            taskName.setFont(new Font(labelFontSize));
            deadLine.setFont(new Font(labelFontSize));

            taskName.setMinWidth(550);
            taskName.setMaxWidth(550);
            HBox task = new HBox(taskName, deadLine);
            taskListView.getItems().add(task);
        }

        for (Map.Entry<String, String> eventInfo : eventInfoMap.entrySet()) {
            Label eventName = new Label(eventInfo.getKey());
            Label startTime = new Label(eventInfo.getValue());
            eventName.setFont(new Font(labelFontSize));
            startTime.setFont(new Font(labelFontSize));

            eventName.setMinWidth(550);
            eventName.setMaxWidth(550);
            HBox task = new HBox(eventName, startTime);
            eventListView.getItems().add(task);
        }
    }
}
