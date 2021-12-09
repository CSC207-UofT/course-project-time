package services.oldpomodororunning;

import entity.OldPomodoroTimer;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimerTask extends TimerTask {
    private final Timer timer;
    private final OldPomodoroTimer oldPomodoroTimer;
    private boolean switchNow;

    public PomodoroTimerTask(Timer timer, OldPomodoroTimer oldPomodoroTimer) {
        this.timer = timer;
        this.oldPomodoroTimer = oldPomodoroTimer;
        this.switchNow = false;
    }

    /**
     * when the timer is over set switchNow to be equal to true to let the program know that it has to start a new timer
     * with the opposite work/break interval
     */
    @Override
    public void run() {
       oldPomodoroTimer.setWorking(!oldPomodoroTimer.getIsWorking());
       this.switchNow = true;
    }

    public void stopTimer() {
        this.timer.cancel();
    }


    public boolean getSwitchNow() {
        return this.switchNow;
    }
}
