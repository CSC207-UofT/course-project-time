package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view_model.TodoListPageViewModel;
import gui.view_model.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TodoListPageController implements Initializable, ViewModelBindingController {

    final double labelFontSize = 15;

    private TodoListPageViewModel viewModel;
    private final ObservableList<Map<String, String>> taskInfoList = FXCollections.observableArrayList();

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private JFXListView<HBox> todoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (TodoListPageViewModel) viewModel;

        Bindings.bindContentBidirectional(this.taskInfoList, this.viewModel.getTaskInfoList());

        for (Map<String, String> taskInfo : this.taskInfoList) {
            Label taskName = new Label(taskInfo.get("taskName"));
            Label deadLine = new Label(taskInfo.get("deadline"));
            taskName.setFont(new Font(labelFontSize));
            deadLine.setFont(new Font(labelFontSize));

            taskName.setMinWidth(550);
            taskName.setMaxWidth(550);

            HBox task = new HBox(taskName, deadLine);
            task.setId(taskInfo.get("id"));
            todoList.getItems().add(task);
        }
    }

    @FXML
    public void addTask() {
        Label taskName = new Label("Sleeping");
        Label deadLine = new Label("Dec 20, 2021, 10:00 PM");
        taskName.setFont(new Font(labelFontSize));
        deadLine.setFont(new Font(labelFontSize));

        taskName.setMinWidth(550);
        taskName.setMaxWidth(550);
        HBox task = new HBox(taskName, deadLine);
        task.setId("11111111111111");
        todoList.getItems().add(task);
    }




}
