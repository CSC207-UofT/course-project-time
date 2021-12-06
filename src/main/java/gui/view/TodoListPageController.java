package gui.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import gui.utility.NavigationHelper;
import gui.view_model.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TodoListPageController implements Initializable, ViewModelBindingController {
    final double labelFontSize = 15;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;

    @FXML
    private JFXListView<HBox> todoList;

    public void addTask() {
        Label taskName = new Label("Sleeping");
        Label deadLine = new Label("December 20, 2021");
        taskName.setFont(new Font(labelFontSize));
        deadLine.setFont(new Font(labelFontSize));

        taskName.setMinWidth(550);
        taskName.setMaxWidth(550);
        HBox task = new HBox(taskName, deadLine);
        todoList.getItems().add(task);
    }

    public void enterTaskPage(MouseEvent event) {
        try {
            NavigationHelper.enterTaskPage(event);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void init(ViewModel viewModel) {
        Label taskName = new Label("CSC207 Project");
        Label deadLine = new Label("December 8, 2021");
        taskName.setFont(new Font(labelFontSize));
        deadLine.setFont(new Font(labelFontSize));

        taskName.setMinWidth(550);
        taskName.setMaxWidth(550);
        HBox task = new HBox(taskName, deadLine);
        todoList.getItems().add(task);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);

    }
}
