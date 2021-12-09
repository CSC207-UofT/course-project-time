package gui.viewmodel;

import services.eventcreation.ICSSaver;

public class SettingsViewModel extends ViewModel{

    private final ICSSaver icsSaver;

    public SettingsViewModel(ICSSaver saver) {
        this.icsSaver = saver;
    }

    public void exportICS() {
        icsSaver.export();
    }
}
