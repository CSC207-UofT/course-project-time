package gui.viewmodel;

import services.eventcreation.ICSSaver;

import java.time.LocalDateTime;

public class SettingsViewModel extends ViewModel{

    private final ICSSaver icsSaver;

    public SettingsViewModel(ICSSaver saver) {
        this.icsSaver = saver;
    }

    public void exportICS(LocalDateTime from, LocalDateTime to) {
        icsSaver.export(from, to);
    }
}
