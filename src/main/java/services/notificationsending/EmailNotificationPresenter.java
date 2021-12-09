package services.notificationsending;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Authenticator;
import java.util.Properties;

/**
 * Sends out email notifications.
 * Keeps a record of whether the user enabled the email notification settings.
 */
public class EmailNotificationPresenter implements NotificationPresenter, SettingsRegistry {
    private boolean enabled;
    private String senderEmail;
    private String password;
    private String userEmail;
    private String subject;

    public EmailNotificationPresenter(boolean enabled, String senderEmail,
                                      String password, String userEmail, String subject) {
        this.enabled = enabled;
        this.senderEmail = senderEmail;
        this.password = password;
        this.userEmail = userEmail;
        this.subject = subject;
    }

    public EmailNotificationPresenter() {
        this(true, "TimeTeam207@gmail.com",
                "CSC207CSC207!", "lin507892@gmail.com", "CSC207 Project Time Notification");
    }

    @Override
    public void presentNotification(String message) {
        if (enabled) {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.socketFactory.port", "465"); //SSL Port： 465
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
            props.put("mail.smtp.port", "465"); //SMTP Port

            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, password);
                }
            };

            Session session = Session.getDefaultInstance(props, auth);

            try {
                MimeMessage msg = new MimeMessage(session);
                msg.addRecipient(Message.RecipientType.TO,new InternetAddress(userEmail));
                msg.setSubject(subject);
                msg.setText(message);
                Transport.send(msg);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void setSettings(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean getSettings() {
        return this.enabled;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
