package datagateway;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class SettingAccessor implements UserSettingsGateway {


    private final SettingDataStructure settings;
    private final Gson gson = new Gson();
    private final String filepath;

    public SettingAccessor(String filepath) {
        this.filepath = filepath;
        this.settings = gson.fromJson(filepath, SettingDataStructure.class);
    }

    @Override
    public String getEmail(String email) {
        return settings.email;

    }

    @Override
    public boolean getDesktopNotifs(boolean notifs) {
        return settings.desktopNotif;
    }

    @Override
    public boolean getEmailNotifs(boolean notifs)  {
        return settings.emailNotif;
    }

    public boolean updateEmail(String email)  {
        settings.email = email;
        return updateData();
    }

    @Override
    public boolean updateEmailNotifs(boolean newNotif) {
        settings.emailNotif = newNotif;
        return updateData();
    }

    @Override
    public boolean updateDesktopNotifs(boolean newNotif) {
        settings.desktopNotif = newNotif;
        return updateData();
    }


    private boolean updateData() {
        String data = gson.toJson(settings);

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
