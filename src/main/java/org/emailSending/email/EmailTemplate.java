package org.emailSending.email;

import javax.activation.MimeType;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailTemplate {
    public static void main(String[] args) {



        String fromAdd = "tejaskarde98@gmail.com";
        String toAdd = "kardetejas21@gmail.com";
        String ccAdd = "tejasKarde21@gmail.com";
        String body = "Welcome to Email Demo Sending MAil";

        try {
            sendMailWithAttachment(fromAdd,toAdd,ccAdd,body);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   public static void sendMailWithoutAttachment(String fromAddress,String toAddress,String ccAddress, String messageBody) throws MessagingException {

              Session session = getSession();
            // Compose Mail
       MimeMessage massage = new MimeMessage(session);

       massage.setFrom(fromAddress);
       massage.addRecipients(Message.RecipientType.TO,toAddress);
       massage.addRecipients(Message.RecipientType.CC,ccAddress);

       massage.setSubject("Mail Form DEMO APP");

       massage.setText(messageBody);

       // 3] Send the MAIL

       Transport.send(massage);
        System.out.println("Email sent Succesfully...");

   }

   private static Session getSession(){

       Properties properties = System.getProperties();
       // attributes to connect to servise provider
       // 1] host 2]Port 3]ssl 4]authentication
       // host
       properties.put("mail.smtp.host","smtp.gmail.com");
       //port
       properties.put("mail.smtp.port","465");
       //ssl
       properties.put("mail.smtp.ssl.enable","true");
       //authentication
       properties.put("mail.smtp.auth","true");

       // Creating connection

       Session session = Session.getInstance(properties, new Authenticator() {
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {
               return  new PasswordAuthentication("tejaskarde98@gmail.com","ijcjiafizvnwxmvc");
           }
       });

        return session;
   }



    // Method for sending multimedia file attacment
    public static  void sendMailWithAttachment(String fromAddress,String toAddress,String ccAddress, String messageBody) throws MessagingException, IOException {

              Session session =  getSession();

              /// now to add multimedia file to attacment to massage
        MimeMessage massage = new MimeMessage(session);

        massage.setFrom(fromAddress);
        massage.addRecipients(Message.RecipientType.TO,toAddress);
        massage.addRecipients(Message.RecipientType.CC,ccAddress);

        massage.setSubject("Mail Form DEMO APP with Attachement");
           // Setting body with attachemt


           
        // for sending attacment we need class

        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart bodyText  = new MimeBodyPart(); // for test
        bodyText.setText(messageBody);

        MimeBodyPart bodyAttachment = new MimeBodyPart(); // for file
        String path  = "C://Users//91708//OneDrive//Pictures//ArtByTejas.png";
       File file  = new File(path);
            bodyAttachment.attachFile(file);

            // Setting in mimeMultipart
            mimeMultipart.addBodyPart(bodyText);
             mimeMultipart.addBodyPart(bodyAttachment);
             
             massage.setContent(mimeMultipart);
             
             // send mail

        Transport.send(massage);

    }


}