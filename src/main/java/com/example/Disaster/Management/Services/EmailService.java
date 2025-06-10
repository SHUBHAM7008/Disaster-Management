package com.example.Disaster.Management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

//    public void sendAlertEmail(List<String> to, String message, String priority) {
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setFrom(fromEmail);
//            for(String email :to)
//            {
//                helper.setTo(email);
//                System.out.println(email);
//            }
//
//            // helper.setSubject(subject);
//            helper.setText(createEmergencyEmail(message, priority), true); // Use HTML content
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new RuntimeException("Failed to send email", e);
//        }
//    }
public void sendAlertEmail(List<String> to, String message, String priority) {
    try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmail);
        helper.setTo(to.toArray(new String[0]));  // Proper way to add multiple recipients
        helper.setSubject("Emergency Alert");
        helper.setText(createEmergencyEmail(message, priority), true);

        mailSender.send(mimeMessage);
        System.out.println("Email sent successfully.");
    } catch (MailException e) {
        System.err.println("MailException: " + e.getMessage());
    } catch (MessagingException e) {
        System.err.println("MessagingException: " + e.getMessage());
    }
}
    private String createEmergencyEmail(String message, String priority) {
        return "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f8d7da; padding: 20px; }" +
                ".alert-container { background-color: #fff; padding: 20px; border-radius: 10px; border: 2px solid #cc0000; }" +
                ".alert-header { color: #cc0000; font-size: 24px; font-weight: bold; text-align: center; }" +
                ".alert-message { margin-top: 20px; font-size: 18px; color: #333; }" +
                ".alert-priority { margin-top: 15px; font-size: 16px; color: #cc0000; font-weight: bold; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='alert-container'>" +
                "<div class='alert-header'>🚨 Emergency Alert 🚨</div>" +
                "<div class='alert-message'>" + message + "</div>" +
                "<div class='alert-priority'>Priority: " + priority.toUpperCase() + "</div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}