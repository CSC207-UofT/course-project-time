package services.oldpomodororunning;

import entity.OldPomodoroTimer;

import java.util.Timer;

public class PomodoroRunner {
    private final OldPomodoroTimer oldPomodoroTimer;
    private final Timer timer;
    private PomodoroTimerTask pomodoroTimerTask;

    public PomodoroRunner(OldPomodoroTimer oldPomodoroTimer) {
        this.oldPomodoroTimer = oldPomodoroTimer;
        this.timer = new Timer();
    }

    /**
     * schedule the timer according to the lengths of time that the user has inputted
     * @param isWorking whether the user is on a "work" interval or "break" interval
     */
    public void startTimer(boolean isWorking){
        this.pomodoroTimerTask = new PomodoroTimerTask(timer, oldPomodoroTimer);
        if (isWorking) {
            timer.schedule(pomodoroTimerTask, (oldPomodoroTimer.getWorkLength())* 60000L);
        }
        else {
            timer.schedule(pomodoroTimerTask, (oldPomodoroTimer.getBreakLength())* 60000L);
        }
    }

    public void stopTimer() {
        pomodoroTimerTask.stopTimer();
    }

    public PomodoroTimerTask getPomodoroTimerTask() {
        return this.pomodoroTimerTask;
    }
}
