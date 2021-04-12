package com.oauth.labapi.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendMail {

    public static String sendemail(String mailto,String content,File f) {

        final String username = "fekrasystems10";
        final String password = "fekra2021";

// https://www.google.com/settings/security/lesssecureapps
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");//TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fekrasystems10@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailto)
            );

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Use this new password to login and you must to change it : "+content);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(f);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setSubject("New Password");
            message.setText("Use this new password to login and you must to change it : "+content);
            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "nice";
    }


    public static void main(String[] args) {

        //SendMail.sendemail("c.hamdaoui98@gmail.com","gdgdgdd");
    }
}