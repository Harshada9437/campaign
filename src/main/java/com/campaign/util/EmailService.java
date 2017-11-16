package com.campaign.util;

import com.campaign.rest.request.campaign.SmtpDetails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.Properties;

public class EmailService {

    public static Boolean newRegistration(String sub, String body, String to, String name, String date, String time, int persons, final SmtpDetails smtpDetails, String s) {
        Boolean isProcessed = Boolean.FALSE;

        // Get system properties
        Properties props = new Properties();

        String MAIL_SMTP_CONNECTIONTIMEOUT ="mail.smtp.connectiontimeout";
        String MAIL_SMTP_TIMEOUT = "mail.smtp.timeout";
        String MAIL_SMTP_WRITETIMEOUT = "mail.smtp.writetimeout";
        String MAIL_SOCKET_TIMEOUT = "60000";
        props.put(MAIL_SMTP_CONNECTIONTIMEOUT, MAIL_SOCKET_TIMEOUT);
        props.put(MAIL_SMTP_TIMEOUT, MAIL_SOCKET_TIMEOUT);
        props.put(MAIL_SMTP_WRITETIMEOUT, MAIL_SOCKET_TIMEOUT);

        props.put("mail.smtp.host", smtpDetails.getHost());
       /* props.put("mail.smtp.socketFactory.port", smtpDetails.getPort());
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
*/
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpDetails.getPort());
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
       // props.setProperty("mail.user", smtpDetails.getEmail());
       // props.setProperty("mail.password", smtpDetails.getPassword());
        
        // Setup mail server
        //props.setProperty("mail.smtp.host", smtpDetails.getHost());

        // Get the default Session object.
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpDetails.getEmail(), smtpDetails.getPassword());
                    }
                });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(smtpDetails.getFrom() +" <" + smtpDetails.getEmail() + ">"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(s+" "+sub);

            String msg = body.replace("%n%",name);
             msg = msg.replace("%d%",DateUtil.format(DateUtil.getTimeStampFromString(date),"dd/MM/yyyy"));
             msg = msg.replace("%t%",time);
             msg = msg.replace("%np%",String.valueOf(persons));
            message.setText(msg);

            // Send message
            Transport.send(message);

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return isProcessed;
    }
}
