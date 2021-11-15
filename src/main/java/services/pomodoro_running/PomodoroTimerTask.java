package main.java.services.pomodoro_running;

import main.java.entity.PomodoroTimer;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimerTask extends TimerTask {
    private Timer timer;
    private PomodoroTimer pomodoroTimer;
    private boolean switchNow;

    public PomodoroTimerTask(Timer timer, PomodoroTimer pomodoroTimer) {
        this.timer = timer;
        this.pomodoroTimer = pomodoroTimer;
        this.switchNow = false;
    }

    /**
     * when the timer is over set switchNow to be equal to true to let the program know that it has to start a new timer
     * with the opposite work/break interval
     */
    @Override
    public void run() {
       pomodoroTimer.setWorking(!pomodoroTimer.getIsWorking());
       this.switchNow = true;
    }

    public void stopTimer() {
        this.timer.cancel();
    }


    public boolean getSwitchNow() {
        return this.switchNow;
    }
}
