package gui.view.todolist;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view.ViewModelBindingController;
import gui.viewmodel.todolist.TodoListPageViewModel;
import gui.viewmodel.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class TodoListPageController implements Initializable, ViewModelBindingController {

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

    public void enterTaskPage(MouseEvent event) {
        try {
            HBox clickedItem = todoList.getSelectionModel().getSelectedItem();
            if (clickedItem != null) {
                viewModel.taskSelected(Long.parseLong(clickedItem.getId()));
                NavigationHelper.enterTaskPage(event);
            }
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

        todoList.getStylesheets().add("todoListPage.css");

        for (Map<String, String> taskInfo : this.taskInfoList) {
            Label taskName = new Label(taskInfo.get("taskName"));
            Label deadLine = new Label(taskInfo.get("deadline"));

            taskName.setMinWidth(550);
            taskName.setMaxWidth(550);

            HBox task = new HBox(taskName, deadLine);
            task.setId(taskInfo.get("id"));
            if (Objects.equals(taskInfo.get("completed"), "true")) {
                task.getStyleClass().add("completedTask");
            } else {
                task.getStyleClass().add("uncompletedTask");
            }

            todoList.getItems().add(task);
        }
    }
}
