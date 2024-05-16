package com.example.project.Untilities;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class EmailSender {
    public static void sendEmail(String recipientEmail, String subject, String body, String apiKey) throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.auth", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("resume.workon.space@gmail.com", "");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("resume.workon.space@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);

        String msg = body;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }


    public static void sendEmail(String recipientEmail, String subject, String body) throws IOException {
        // URL endpoint
        String url = "https://api.postmarkapp.com/email";

        // JSON request body
        String jsonBody = "{"
                + "\"From\": \"20110233@student.hcmute.edu.vn\","
                + "\"To\": \"" + recipientEmail + "\","
                + "\"Subject\": \"" + subject + "\","
                + "\"TextBody\": \"" + body + "\","
                + "\"HtmlBody\": \"<html><body><strong>" + body + "</strong></body></html>\","
                + "\"MessageStream\": \"outbound\""
                + "}";

        // Create URL object
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Set request method
        con.setRequestMethod("POST");

        // Set request headers
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("X-Postmark-Server-Token", "7ba34be9-4c74-4eef-9985-e45a7b44cfee");

        // Enable output for POST data
        con.setDoOutput(true);

        // Write JSON data to request body
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(jsonBody);
            wr.flush();
        }

        // Get response code
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        // Read response body
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            // Print response body
            System.out.println("Response Body : " + response.toString());
        }
    }




}
