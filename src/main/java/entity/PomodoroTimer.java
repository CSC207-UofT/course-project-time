package entity;

public class PomodoroTimer {
    private long startTime;
    private final boolean isWork;
    private final long breakDuration;
    private final long workDuration;
    private final boolean newStart;

    public PomodoroTimer(long startTime, boolean isWork, long breakDuration, long workDuration, boolean newStart) {
        this.startTime = startTime;
        this.isWork = isWork;
        this.breakDuration = breakDuration;
        this.workDuration = workDuration;
        this.newStart = newStart;
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

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
