package tw.com.cha102.core.service;


import org.springframework.messaging.MessagingException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
    // 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
    public void sendMail(String to, String subject, String messageText) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            final String myGmail = "ezdom7372@gmail.com";
            final String myGmail_password = "evtkrrzqcubolmjz";
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myGmail, myGmail_password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myGmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // 設定信中的主旨
            message.setSubject(subject);
            // 設定信中的內容
            message.setText(messageText);

            Transport.send(message);
            System.out.println("傳送成功!");
        } catch (MessagingException e) {
            System.out.println("傳送失敗!");
            e.printStackTrace();
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
