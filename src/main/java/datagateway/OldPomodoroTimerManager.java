package datagateway;

import entity.OldPomodoroTimer;

public class OldPomodoroTimerManager {

    public OldPomodoroTimer createPomodoroTimer(int workTime, int breakTime) {
        return new OldPomodoroTimer(workTime, breakTime);
    }
}
