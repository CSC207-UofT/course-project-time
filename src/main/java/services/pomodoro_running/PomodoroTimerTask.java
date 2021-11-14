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
