package gui.utility;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
 * A helper class to switch between views and to initialize
 * navigation panels when view pages are initially loaded.
 */
public class NavigationHelper {

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
     * Changes view to the monthly calendar page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public void enterMonthlyCalendarPage(Event event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/monthlyCalendar.fxml")));
        setNewScene(event, root);
    }

    /**
     * Changes view to the weekly calendar page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    private void enterWeeklyCalendarPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/weeklyCalendar.fxml")));
        setNewScene(event, root);
    }

    /**
     * Changes view to the daily calendar page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    private void enterDailyCalendarPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/dailyCalendar.fxml")));
        setNewScene(event, root);
    }

    /**
     * Switches the page view based on the given calendar type
     * @param event the event that triggered the request to change view
     * @param calendarType the type of calendar page to switch to (weekly, monthly, and daily)
     * @throws IOException if the resource file cannot be found
     */
    public void switchCalendarPageType(ActionEvent event, String calendarType) throws IOException {
        switch (calendarType) {
            case "Month" -> enterMonthlyCalendarPage(event);
            case "Week" -> enterWeeklyCalendarPage(event);
            case "Day" -> enterDailyCalendarPage(event);
            default -> System.out.println("Should not have reached here");
        }
    }

    /**
     * Changes view to the home page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public void enterHomePage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/basicPage.fxml")));
        setNewScene(event, root);
    }

    /**
     * Changes view to the pomodoro page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public void enterPomodoroPage(MouseEvent event) throws IOException {

    }

    /**
     * Changes view to the settings page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public void enterSettingsPage(MouseEvent event) throws IOException {

    }

    /**
     * Changes view to the todolist page.
     * @param event the event that triggered the request to change view
     * @throws IOException if the resource file cannot be found
     */
    public void enterTodoListPage(MouseEvent event) throws IOException {

    }

    /**
     * Sets a new scene. Used by the other methods when handling requests to change view pages.
     * @param event the event that triggered the request to change view
     * @param root the resource to change the view page to
     */
    private void setNewScene(Event event, Parent root) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(root, 1000, 800));
        currentStage.show();
    }
}
