package gui.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoListPageController implements Initializable, ViewModelBindingController {
    @FXML
    private JFXListView<HBox> todoList;

    public void addTask() {
        Label taskName = new Label("CSC207 Project");
        Label deadLine = new Label("December 8, 2021");
        HBox task = new HBox(350, taskName, deadLine);
        todoList.getItems().add(task);
    }

    @Override
    public void init(ViewModel viewModel) {
        Label taskName = new Label("CSC207 Project");
        Label deadLine = new Label("December 8, 2021");
        HBox task = new HBox(350, taskName, deadLine);
        todoList.getItems().add(task);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
