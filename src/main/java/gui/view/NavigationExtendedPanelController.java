package gui.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NavigationExtendedPanelController {

    @FXML
    private JFXHamburger menuIcon;

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
    void enterCalendarPage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/monthlyCalendar.fxml")));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root, 1000, 800));
        currentStage.show();
    }

    @FXML
    void enterHomePage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/basicPage.fxml")));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root, 1000, 800));
        currentStage.show();
    }

    @FXML
    void enterPomodoroPage(MouseEvent event) {

    }

    @FXML
    void enterSettingsPage(MouseEvent event) {

    }

    @FXML
    void enterTodoListPage(MouseEvent event) {

    }

    @FXML
    void menuIconClicked(MouseEvent event) {
        Scene currentScene = ((Node) event.getSource()).getScene();
        JFXDrawer extendedNavPanel = (JFXDrawer) currentScene.lookup("#extendedNavPanel");
        extendedNavPanel.setVisible(false);
        extendedNavPanel.close();
    }

}
