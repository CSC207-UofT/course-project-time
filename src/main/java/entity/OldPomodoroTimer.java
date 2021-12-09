package entity;

/**
 * For the GUI, this class and any other pomodoro class/package with the word "old" in front of it are no longer being
 * used, since the way the pomodoro system is being implemented has changed tremendously.
 * We have kept these classes around so that the console application can still be run.
 * This is why there are two pomodoro entities (one for the GUI and one for the console app)
 */
public class OldPomodoroTimer {
    private final int workLength;
    private final int breakLength;
    private boolean isWorking;

    /***
     *
     * @param workLength  the length of time in minutes the user wants to work for
     * @param breakLength the length of time in minutes the user wants to break for
     */
    public OldPomodoroTimer(int workLength, int breakLength) {
        this.workLength = workLength;
        this.breakLength = breakLength;
        this.isWorking = true;
    }

    public int getWorkLength() {
        return this.workLength;
    }

    public int getBreakLength() {
        return this.breakLength;
    }

    public boolean getIsWorking() {
        return this.isWorking;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

}
