package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.viewmodel.TodoListPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private final ObservableList<Map<String, String>> taskInfoList = FXCollections.observableArrayList();

    private TaskPageController taskPage;

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
            HBox clickedItem = todoList.getSelectionModel().getSelectedItem();
            NavigationHelper.enterTaskPage(event);
            taskPage.displayTask(Long.parseLong(clickedItem.getId()));
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
}
