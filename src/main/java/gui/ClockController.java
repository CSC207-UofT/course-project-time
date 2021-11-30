package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import services.pomodoro_running.TimeFormatter;

import java.util.concurrent.TimeUnit;

public class ClockController {

    private AnimationTimer timer;
    private GraphicsContext gc;
    private Label pomodoroLabel;
    private final float brushSize = 30;

    private final long workDuration = 1;
    private final long breakDuration = 1;
    private long currentDuration = workDuration;
    private boolean isBreak = false;

    private long startNano;
    private boolean newStart;

    static TimeFormatter timeFormatter = new TimeFormatter();

    @FXML
    private Canvas canvas;


    @FXML
    public void initialize() {

        gc = canvas.getGraphicsContext2D();

        gc.setLineWidth(brushSize);
        setWork(gc);

        pomodoroLabel = new Label();

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


    @FXML
    void startClock(MouseEvent event) {
        newStart = true;
        timer.start();
    }

    @FXML
    void resetClock(MouseEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        timer.stop();
    }

}
