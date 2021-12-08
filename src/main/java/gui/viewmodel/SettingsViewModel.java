package gui.viewmodel;

import services.eventcreation.ICSSaver;

public class SettingsViewModel extends ViewModel{

    private final ICSSaver saver;

    public SettingsViewModel(ICSSaver saver) {
        this.saver = saver;
    }

    void exportToICS() {
        saver.export();
    }
}
