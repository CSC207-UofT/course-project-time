package services.consolepomodororunning;

import entity.ConsolePomodoroTimer;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimerTask extends TimerTask {
    private final Timer timer;
    private final ConsolePomodoroTimer consolePomodoroTimer;
    private boolean switchNow;

    public PomodoroTimerTask(Timer timer, ConsolePomodoroTimer consolePomodoroTimer) {
        this.timer = timer;
        this.consolePomodoroTimer = consolePomodoroTimer;
        this.switchNow = false;
    }

    /**
     * when the timer is over set switchNow to be equal to true to let the program know that it has to start a new timer
     * with the opposite work/break interval
     */
    @Override
    public void run() {
       consolePomodoroTimer.setWorking(!consolePomodoroTimer.getIsWorking());
       this.switchNow = true;
    }

    public void stopTimer() {
        this.timer.cancel();
    }


    public boolean getSwitchNow() {
        return this.switchNow;
    }
}
