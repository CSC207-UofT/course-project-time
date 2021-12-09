package entity;

public class PomodoroTimer {
    private final long startTime;
    private final boolean isWork;
    private final long breakDuration;
    private final long workDuration;

    public PomodoroTimer(long startTime, boolean isWork, long breakDuration, long workDuration) {
        this.startTime = startTime;
        this.isWork = isWork;
        this.breakDuration = breakDuration;
        this.workDuration = workDuration;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean getIsWork() {
        return isWork;
    }

    public long getBreakDuration() {
        return breakDuration;
    }

    public long getWorkDuration() {
        return workDuration;
    }

}
