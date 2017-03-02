package common;

import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by dmitrii on 27.02.17.
 */
public class Mailer {
    private static String user = "logger2017stc@gmail.com";
    static private Logger logger = Logger.getLogger(Mailer.class);

    public static void sendEmail(String email, String text){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, "");
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user));

            message.setSubject("Notification OnlineTours!");
            message.setText(text);

            Transport.send(message);

            logger.trace("Mail sended to " + email);

        } catch (javax.mail.MessagingException e) {
            //throw new RuntimeException(e);
            logger.error("Mail not send");
        }
    }
}
