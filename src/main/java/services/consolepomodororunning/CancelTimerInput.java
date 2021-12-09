package services.consolepomodororunning;

import java.util.Objects;
import java.util.Scanner;

public class CancelTimerInput implements Runnable {
    private boolean cancel = false;


    /**
     * continuously check if the user wants to cancel the timer
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            if (Objects.equals(scanner.nextLine(), "c")) {
                this.cancel = true;
                return;
            }
        }
    }

    public boolean getCancel() {
        return this.cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
