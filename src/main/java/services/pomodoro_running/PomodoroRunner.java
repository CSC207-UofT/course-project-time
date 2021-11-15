package main.java.services.pomodoro_running;

import main.java.entity.PomodoroTimer;

import java.util.Timer;

public class PomodoroRunner {
    private final PomodoroTimer pomodoroTimer;
    private final Timer timer;
    private PomodoroTimerTask pomodoroTimerTask;

    public PomodoroRunner(PomodoroTimer pomodoroTimer) {
        this.pomodoroTimer = pomodoroTimer;
        this.timer = new Timer();
    }

    /**
     * schedule the timer according to the lengths of time that the user has inputted
     * @param isWorking whether the user is on a "work" interval or "break" interval
     */
    public void startTimer(boolean isWorking){
        this.pomodoroTimerTask = new PomodoroTimerTask(timer, pomodoroTimer);
        if (isWorking) {
            timer.schedule(pomodoroTimerTask, (pomodoroTimer.getWorkLength())* 60000L);
        }
        else {
            timer.schedule(pomodoroTimerTask, (pomodoroTimer.getBreakLength())* 60000L);
        }
    }

    public void stopTimer() {
        pomodoroTimerTask.stopTimer();
    }

    public PomodoroTimerTask getPomodoroTimerTask() {
        return this.pomodoroTimerTask;
    }
}
