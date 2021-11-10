package main.java.use_case;

import main.java.entity.PomodoroTimer;

import java.util.Timer;

public class PomodoroObserver {
    private PomodoroTimer pomodoroTimer;
    private Timer timer;
    private boolean isWorking;
    private boolean isOnBreak;

    public PomodoroObserver(PomodoroTimer pomodoroTimer) {
        this.pomodoroTimer = pomodoroTimer;
        this.timer = new Timer();
        this.isOnBreak = false;
        this.isWorking = false;
    }

    public void startTimer(boolean isWorking){
        if (this.pomodoroTimer.getCanceled()) {
            return;
        }
        if (isWorking) {
            timer.schedule(new PomodoroTimerTask(), pomodoroTimer.getWorkLength());
        }
        else {
            timer.schedule(new PomodoroTimerTask(), pomodoroTimer.getBreakLength());
        }
        startTimer(!isWorking);
    }
}
