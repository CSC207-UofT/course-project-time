package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view_model.TodoListPageViewModel;
import gui.view_model.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TodoListPageController implements Initializable, ViewModelBindingController {

    final double labelFontSize = 15;

    private TodoListPageViewModel viewModel;
    private final ObservableMap<String, String> taskInfoMap = FXCollections.observableHashMap();

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

    public void enterTaskPage(MouseEvent event) {
        try {
            NavigationHelper.enterTaskPage(event);
            HBox clickedItem = todoList.getSelectionModel().getSelectedItem();
            System.out.println(clickedItem.getId());  // todo when id can be obtained, use id to find task info
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void enterAddTaskPage(MouseEvent event) {
        try {
            NavigationHelper.enterAddTaskPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void init(ViewModel viewModel) {
        this.viewModel = (TodoListPageViewModel) viewModel;

        Bindings.bindContentBidirectional(this.taskInfoMap, this.viewModel.getTaskInfoMap());

        for (Map.Entry<String, String> taskInfo : taskInfoMap.entrySet()) {
            Label taskName = new Label(taskInfo.getKey());
            Label deadLine = new Label(taskInfo.getValue());
            taskName.setFont(new Font(labelFontSize));
            deadLine.setFont(new Font(labelFontSize));

            taskName.setMinWidth(550);
            taskName.setMaxWidth(550);
            HBox task = new HBox(taskName, deadLine);
            todoList.getItems().add(task);
        }
    }
}
