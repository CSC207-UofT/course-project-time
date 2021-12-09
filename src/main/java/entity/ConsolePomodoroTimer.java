package entity;

public class ConsolePomodoroTimer {
    private final int workLength;
    private final int breakLength;
    private boolean isWorking;

    /***
     *
     * @param workLength  the length of time in minutes the user wants to work for
     * @param breakLength the length of time in minutes the user wants to break for
     */
    public ConsolePomodoroTimer(int workLength, int breakLength) {
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
