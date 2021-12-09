package data_gateway;

public interface UserSettingsGateway {
    String getEmail(String email);
    boolean getDesktopNotifs(boolean notifs);
    boolean getEmailNotifs(boolean notifs);

    boolean updateEmail(String email);
    boolean updateEmailNotifs(boolean newNotif);
    boolean updateDesktopNotifs(boolean newNotif);
}
