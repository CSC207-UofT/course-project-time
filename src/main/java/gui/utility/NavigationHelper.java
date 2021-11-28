package gui.utility;

import com.jfoenix.controls.JFXDrawer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * A utility class that contains static methods
 * related to the navigation panel across all views.
 */
public final class NavigationHelper {

    private NavigationHelper() {
    }

    /**
     * Initializes the navigation panels in a view page by adding them into the JFXDrawers.
     * @param extendedNavPanel the navigation panel that is only shown when the menu icon is pressed
     * @param collapsedNavPanel the navigation panel that is always shown on the view page
     */
    public static void initializeNavPanel(JFXDrawer extendedNavPanel, JFXDrawer collapsedNavPanel) {
        try {
            VBox extendedPanel = FXMLLoader.load(Objects.requireNonNull(NavigationHelper.class.getResource("/navigationExtendedPanel.fxml")));
            extendedNavPanel.setSidePane(extendedPanel);
            extendedNavPanel.setMinWidth(0);
            extendedNavPanel.setVisible(false);
            extendedNavPanel.close();

            VBox collapsedPanel = FXMLLoader.load(Objects.requireNonNull(NavigationHelper.class.getResource("/navigationCollapsedPanel.fxml")));
            collapsedNavPanel.setSidePane(collapsedPanel);
            collapsedNavPanel.setMinWidth(0);
            collapsedNavPanel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes view to the calendar page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public static void enterCalendarPage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavigationHelper.class.getResource("/monthlyCalendar.fxml")));
        setNewScene(event, root);
    }

    /**
     * Changes view to the home page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public static void enterHomePage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(NavigationHelper.class.getResource("/basicPage.fxml")));
        setNewScene(event, root);
    }

    /**
     * Changes view to the pomodoro page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public static void enterPomodoroPage(MouseEvent event) throws IOException {

    }

    /**
     * Changes view to the settings page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public static void enterSettingsPage(MouseEvent event) throws IOException {

    }

    /**
     * Changes view to the todolist page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public static void enterTodoListPage(MouseEvent event) throws IOException {

    }

    /**
     * Sets a new scene. Used by the other methods when handling requests to change view pages.
     * @param event the event that triggered the request to change view
     * @param root the resource to change the view page to
     */
    private static void setNewScene(MouseEvent event, Parent root) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root, 1000, 800));
        currentStage.show();
    }
}
