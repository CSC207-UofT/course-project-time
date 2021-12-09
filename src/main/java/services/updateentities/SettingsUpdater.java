package services.updateentities;

import datagateway.UserSettingsGateway;

public class SettingsUpdater {

    private UserSettingsGateway settingsAccessor;

    public void updateSettings(String email, boolean desktopNotifs, boolean emailNotifs) {
        settingsAccessor.updateEmail(email);
        settingsAccessor.updateDesktopNotifs(desktopNotifs);
        settingsAccessor.updateEmailNotifs(emailNotifs);
    }

}