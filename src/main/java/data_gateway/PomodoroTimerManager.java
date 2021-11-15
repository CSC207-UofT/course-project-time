package data_gateway;

import entity.PomodoroTimer;

public class PomodoroTimerManager {
    private PomodoroTimer pomodoroTimer;

    public PomodoroTimer createPomodoroTimer(int workTime, int breakTime) {
        this.pomodoroTimer = new PomodoroTimer(workTime, breakTime);
        return pomodoroTimer;
    }

    public void stopPomodoroTimer() {
        pomodoroTimer.setCanceled(true);
    }
}
