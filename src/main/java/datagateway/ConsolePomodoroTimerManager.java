package datagateway;

import entity.ConsolePomodoroTimer;

public class ConsolePomodoroTimerManager {

    public ConsolePomodoroTimer createPomodoroTimer(int workTime, int breakTime) {
        return new ConsolePomodoroTimer(workTime, breakTime);
    }
}
