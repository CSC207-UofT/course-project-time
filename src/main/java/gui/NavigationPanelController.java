package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigationPanelController {

    @FXML
    private JFXButton calendar;

    @FXML
    private JFXButton home;

    @FXML
    private JFXButton pomodoro;

    @FXML
    private JFXButton settings;

    @FXML
    private JFXButton todolist;

    @FXML
    void enterCalendarPage(MouseEvent event) {

    }

    @FXML
    void enterHomePage(MouseEvent event) {

    }

    @FXML
    void enterPomodoroPage(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pomodoroPage.fxml")));
        Scene pomodoroScene = new Scene(newRoot);
        stageFromClick(event).setScene(pomodoroScene);
    }

    @FXML
    void enterSettingsPage(MouseEvent event) {

    }

    @FXML
    void enterTodoListPage(MouseEvent event) {

    }

    /**
     * Returns the stage in which a mouse event took place
     * @param event The mouse event in question
     * @return Stage The stage of the event
     */
    private Stage stageFromClick(MouseEvent event ) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();

    }

}
