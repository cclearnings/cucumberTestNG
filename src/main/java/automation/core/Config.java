package automation.core;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Properties;

public class Config {

    public static final String url = "https://sticsoftio.sharepoint.com/sites/HubStandard";
    public static final boolean sendEmail = false;


    public static String getTimStamp() {
        Timestamp instant = Timestamp.from(Instant.now());
        return instant.toString();
    }


    public static JSONObject testData(String fileName) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/test/resources/data/config.json"));
            jsonObject = (JSONObject) obj;

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }
        return jsonObject;

    }


    public static void sendReport() {

        String to = "chandra.chandragiri@sticsoftsolutions.com";
        String email2 = "chandrasekhar.chandragiri_ext@novartis.com";

        String from = "sticsoft.qa@gmail.com";

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("sticsoft.qa@gmail.com", "Stic@2018");

            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Automation Job Results On: " + Browser.getTimStamp() );

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("Hello Team," +
                    "Please find the automation job results in attachment\n" +
                    "\n" +
                    "Please reach out to Divya Naranasa for any other queries related to Job \n" +
                    "\n" +
                    "\n" +
                    "Site URL: " + url +
                    "Regards\n" +
                    "Team Automation\n" +
                    "STIC SOFT E SOLUTIONS"
            );

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("target/cucumber-report/report.html"));
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }


    }
}
