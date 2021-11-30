package gui;

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
import services.pomodoro_running.PomodoroRunner;
import services.pomodoro_running.TimeFormatter;


import java.util.concurrent.TimeUnit;

public class ClockController {

    private AnimationTimer timer;
    private GraphicsContext gc;
    private final float brushSize = 30;

    private long workDuration = 25;
    private long breakDuration = 5;
    private long currentDuration = workDuration;
    private boolean isBreak = false;

    private long startNano;
    private boolean newStart;

    private final Alert error = new Alert(Alert.AlertType.ERROR);

    static TimeFormatter timeFormatter = new TimeFormatter();

    @FXML
    private Canvas canvas;

    @FXML TextField breakTimeText;

    @FXML TextField workTimeText;



    @FXML
    public void initialize() {

        gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);

        gc.setLineWidth(brushSize);
        setWork(gc);


        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if(newStart) {
                    startNano = now;
                    newStart = false;
                }

                if (!workTimeText.getText().isEmpty()) {
                    workDuration = Long.parseLong(workTimeText.getText());
                }
                else if (!breakTimeText.getText().isEmpty()) {
                    breakDuration = Long.parseLong(breakTimeText.getText());
                }

                double elapsedTime = (double)(now - startNano);
                double angle = elapsedTime / TimeUnit.NANOSECONDS.convert(currentDuration, TimeUnit.MINUTES) * 360;
                String timeLeft = timeFormatter.formatTime(elapsedTime, currentDuration);


                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                drawTimer(gc, timeLeft);
                gc.strokeArc(brushSize, brushSize, canvas.getWidth() - brushSize * 2,
                        canvas.getHeight() - brushSize * 2, 90, -angle, ArcType.OPEN);

                if(elapsedTime >= TimeUnit.NANOSECONDS.convert(currentDuration, TimeUnit.MINUTES)) {
                    switchCircle(gc);
                }
            }
        };
    }

    private void setWork(GraphicsContext gc) {
        isBreak = false;
        gc.setStroke(Color.LIGHTGREEN);
        currentDuration = workDuration;
        newStart = true;
    }

    private void setBreak(GraphicsContext gc) {
        isBreak = true;
        gc.setStroke(Color.TEAL);
        currentDuration = breakDuration;
        newStart = true;
    }

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

    @FXML
    void startClock(MouseEvent event) {
        if (!workTimeText.getText().isEmpty()) {
            String potentialWorkTime = workTimeText.getText();
            if (!userInputChecker(potentialWorkTime)){
                error.setTitle("Invalid input");
                error.setContentText("Pomodoro timer will be started with default work interval");
                error.showAndWait();
                workTimeText.clear();
            }
            else {
                workDuration = Long.parseLong(potentialWorkTime);
                currentDuration = workDuration;
            }
        }
        else if (!breakTimeText.getText().isEmpty()) {
            String potentialBreakTime = breakTimeText.getText();
            if (!userInputChecker(potentialBreakTime)){
                error.setTitle("Invalid input");
                error.setContentText("Pomodoro timer will be started with default break interval");
                error.showAndWait();
                breakTimeText.clear();
            }
            else {
                breakDuration = Long.parseLong(potentialBreakTime);
            }
        }
        newStart = true;
        workTimeText.setEditable(false);
        breakTimeText.setEditable(false);
        timer.start();
    }

    /**
     * checks if the user input is an integer
     */
    boolean userInputChecker(String text) {
        return text.matches("[0-9]*");
    }

    @FXML
    void resetClock(MouseEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        timer.stop();
        breakTimeText.setEditable(true);
        workTimeText.setEditable(true);
    }

}
