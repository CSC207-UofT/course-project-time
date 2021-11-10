package main.java.interface_adapters;

import main.java.use_case.PomodoroObserver;

public class PomodoroController {
    private PomodoroObserver pomodoroObserver;

    public PomodoroController(PomodoroObserver pomodoroObserver) {
        this.pomodoroObserver = pomodoroObserver;
    }

    public void startTimer() {

    }
}
