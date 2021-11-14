package main.java.use_case;

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
       timer.cancel();
       pomodoroTimer.setWorking(!pomodoroTimer.getIsWorking());
       this.switchNow = true;
       System.out.println("REACHED END OF TIMER");

    }

    public boolean getSwitchNow() {
        return this.switchNow;
    }
}
