package services.consolepomodororunning;

import entity.ConsolePomodoroTimer;

import java.util.Timer;

public class PomodoroRunner {
    private final ConsolePomodoroTimer consolePomodoroTimer;
    private final Timer timer;
    private PomodoroTimerTask pomodoroTimerTask;

    public PomodoroRunner(ConsolePomodoroTimer consolePomodoroTimer) {
        this.consolePomodoroTimer = consolePomodoroTimer;
        this.timer = new Timer();
    }

    /**
     * schedule the timer according to the lengths of time that the user has inputted
     * @param isWorking whether the user is on a "work" interval or "break" interval
     */
    public void startTimer(boolean isWorking){
        this.pomodoroTimerTask = new PomodoroTimerTask(timer, consolePomodoroTimer);
        if (isWorking) {
            timer.schedule(pomodoroTimerTask, (consolePomodoroTimer.getWorkLength())* 60000L);
        }
        else {
            timer.schedule(pomodoroTimerTask, (consolePomodoroTimer.getBreakLength())* 60000L);
        }
    }

    public void stopTimer() {
        pomodoroTimerTask.stopTimer();
    }

    public PomodoroTimerTask getPomodoroTimerTask() {
        return this.pomodoroTimerTask;
    }
}
