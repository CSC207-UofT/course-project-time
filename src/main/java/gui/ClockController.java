package gui;

import com.jfoenix.controls.JFXDrawer;
import gui.utility.NavigationHelper;
import gui.view.ViewModelBindingController;
import gui.viewmodel.ViewModel;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import services.pomodororunning.TimeFormatter;
import datagateway.pomodoro.PomodoroManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ClockController implements ViewModelBindingController {

    private AnimationTimer timer;
    private GraphicsContext gc;
    private final float BRUSHSIZE = 30;

    private final long DEFAULT_WORK = 25;
    private final long DEFAULT_BREAK = 5;
    private long workDuration = DEFAULT_WORK;
    private long breakDuration = DEFAULT_BREAK;
    private long currentDuration = workDuration;
    private boolean isBreak = false;

    private long startNano;
    private boolean newStart = true;

    private final Alert error = new Alert(Alert.AlertType.INFORMATION);

    private final PomodoroManager pomodoroManager = new PomodoroManager();

    static final TimeFormatter timeFormatter = new TimeFormatter();

    @FXML
    private Canvas canvas;

    @FXML TextField breakTimeText;

    @FXML TextField workTimeText;

    @FXML
    private JFXDrawer collapsedNavPanel;

    @FXML
    private JFXDrawer extendedNavPanel;



    @FXML
    public void initialize() {
        try {
            pomodoroManager.loadTimer("PomodoroData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        NavigationHelper.initializeNavPanel(extendedNavPanel, collapsedNavPanel);
        gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);

        gc.setLineWidth(BRUSHSIZE);
        setWork(gc);


        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if(newStart) {
                    startNano = now;
                    newStart = false;
                }

                double elapsedTime = (double)(now - startNano);
                double angle = elapsedTime / TimeUnit.NANOSECONDS.convert(currentDuration, TimeUnit.MINUTES) * 360;
                String timeLeft = timeFormatter.formatTime(elapsedTime, currentDuration);


                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                drawTimer(gc, timeLeft);
                gc.strokeArc(BRUSHSIZE, BRUSHSIZE, canvas.getWidth() - BRUSHSIZE * 2,
                        canvas.getHeight() - BRUSHSIZE * 2, 90, -angle, ArcType.OPEN);

                if(elapsedTime >= TimeUnit.NANOSECONDS.convert(currentDuration, TimeUnit.MINUTES)) {
                    switchCircle(gc);
                }
            }
        };
        if (!(pomodoroManager.getPomodoroTimer() == null)) {
            setDefaults();
            workTimeText.setEditable(false);
            breakTimeText.setEditable(false);
            timer.start();
        }
    }

    private void setDefaults() {
        workDuration = pomodoroManager.getPomodoroTimer().getWorkDuration();
        breakDuration = pomodoroManager.getPomodoroTimer().getBreakDuration();

        startNano = calculateCurrentIntervalsStartTime(
                pomodoroManager.getPomodoroTimer().getIsWork(), pomodoroManager.getPomodoroTimer().getStartTime());

        if (!isBreak) {
            currentDuration = workDuration;
            setWork(gc);
        }
        else{
            currentDuration = breakDuration;
            setBreak(gc);
        }

        newStart = false;
    }

    private long calculateCurrentIntervalsStartTime(boolean isWork, long startTime) {
        long elapsedTime = System.nanoTime() - startTime;
        long remainder = 0;
        long tempTime;
        while (elapsedTime > 0) {
            if (isWork) {
                tempTime = elapsedTime - TimeUnit.NANOSECONDS.convert(workDuration, TimeUnit.MINUTES);
                if (tempTime < 0) {
                    remainder = elapsedTime;
                    elapsedTime = -1;
                }
                else {
                    elapsedTime = tempTime;
                }
            }
            else {
                tempTime = elapsedTime - TimeUnit.NANOSECONDS.convert(breakDuration, TimeUnit.MINUTES);
                if (tempTime < 0) {
                    remainder = elapsedTime;
                    elapsedTime = -1;
                }
                else {
                    elapsedTime = tempTime;
                }

            }
            isWork = !isWork;
        }
        isBreak = isWork;
        return System.nanoTime() - remainder;
    }

    /**
     * Set the clock to a work state
     * @param gc The graphics context on which the clock is drawn
     */
    private void setWork(GraphicsContext gc) {
        isBreak = false;
        gc.setStroke(Color.LIGHTGREEN);
        currentDuration = workDuration;
        newStart = true;
    }

    /**
     * Set the clock to a break state
     * @param gc The graphics context on which the clock is drawn
     */
    private void setBreak(GraphicsContext gc) {
        isBreak = true;
        gc.setStroke(Color.TEAL);
        currentDuration = breakDuration;
        newStart = true;
    }

    /**
     * Switch between a break state and a work state
     * @param gc The graphics context on which the circle is drawn
     */
    private void switchCircle(GraphicsContext gc) {
        isBreak = !isBreak;
        if(isBreak) {
            setBreak(gc);
        } else {
            setWork(gc);
        }
    }

    private void drawTimer(GraphicsContext gc, String time) {

        gc.setFont(Font.font("Castellar", 100));
        gc.fillText(time, canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth() * 0.5);
    }

    /**
     * Begin the clock, called by the start button
     * @param event Mouse click event from JavaFX
     */
    @FXML
    void startClock(MouseEvent event) {
        if (!workTimeText.getText().isEmpty()) {
            String potentialWorkTime = workTimeText.getText();
            if (invalidTimeChecker(potentialWorkTime)){
                error.setTitle("Invalid input");
                error.setContentText("Pomodoro timer will be started with default work interval");
                error.showAndWait();
                workTimeText.clear();
                workDuration = DEFAULT_WORK;
            }
            else {
                workDuration = Long.parseLong(potentialWorkTime);
                currentDuration = workDuration;
            }
        }

        if (!breakTimeText.getText().isEmpty()) {
            String potentialBreakTime = breakTimeText.getText();
            if (invalidTimeChecker(potentialBreakTime)){
                error.setTitle("Invalid input");
                error.setContentText("Pomodoro timer will be started with default break interval");
                error.showAndWait();
                breakTimeText.clear();
                breakDuration = DEFAULT_BREAK;
            }
            else {
                breakDuration = Long.parseLong(potentialBreakTime);
            }
        }
        newStart = true;
        workTimeText.setEditable(false);
        breakTimeText.setEditable(false);

        pomodoroManager.createTimer(System.nanoTime(),!isBreak, breakDuration, workDuration, newStart);
        try {
            pomodoroManager.saveTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer.start();
    }

    /**
     * checks if the user input is an integer
     */
    boolean invalidTimeChecker(String text) {
        return !text.matches("[0-9]*");
    }

    @FXML
    void resetClock(MouseEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        timer.stop();
        breakTimeText.setEditable(true);
        workTimeText.setEditable(true);
    }

    @Override
    public void init(ViewModel viewModel) {

    }
}

