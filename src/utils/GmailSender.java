package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSender {

    public static void sendEmail(String toEmailAddress, String fromEmailAddress, String appPassword, String subject,
            String bodyText) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(prop);
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(fromEmailAddress));

            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress)); // setting "TO" email address
            message.setSubject(subject); // setting subject
            message.setText(bodyText);  // setting body

            System.out.println("Sending Email...");
            Transport t = session.getTransport("smtp");

            t.connect(fromEmailAddress, appPassword);
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (MessagingException e) {

            e.printStackTrace();
        }
        System.out.println("Email sent successfully...");
    }
}

