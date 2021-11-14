package main.java.use_case;

import main.java.entity.PomodoroTimer;

import java.util.Timer;

public class PomodoroRunner {
    private final PomodoroTimer pomodoroTimer;
    private final Timer timer;
    private final PomodoroTimerTask pomodoroTimerTask;

    public PomodoroRunner(PomodoroTimer pomodoroTimer) {
        this.pomodoroTimer = pomodoroTimer;
        this.timer = new Timer();
        this.pomodoroTimerTask = new PomodoroTimerTask(timer, pomodoroTimer);
    }

    /**
     * schedule the timer according to the lengths of time that the user has inputted
     * @param isWorking whether or not the user is on a "work" interval or "break" interval
     */
    public void startTimer(boolean isWorking){
        if (isWorking) {
            timer.schedule(pomodoroTimerTask, (pomodoroTimer.getWorkLength())* 60000L);
        }
        else {
            timer.schedule(pomodoroTimerTask, (pomodoroTimer.getBreakLength())* 60000L);
        }
    }

    public PomodoroTimerTask getPomodoroTimerTask() {
        return this.pomodoroTimerTask;
    }

    public PomodoroTimer getPomodoroTimer() {
        return this.pomodoroTimer;
    }
}
