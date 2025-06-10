package com.example.Disaster.Management.Controllers;




import com.example.Disaster.Management.Repositories.EmailRepository;
import com.example.Disaster.Management.Services.EmailService;
import com.example.Disaster.Management.Tables.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/send-notification")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    EmailRepository emailRepository;

    @PostMapping
    public String sendNotification(@RequestBody EmailRequest request) {
        // Use getTo() to retrieve the email addresses
        // Use getSubject() to get the subject of the email
        List<Emails> list = emailRepository.findAll().stream().toList();
        List<String> list1= new ArrayList<>();

        for(Emails e :list){
            list1.add(e.getMail());
            System.out.println("My Mail"+e.getMail());
        }
        emailService.sendAlertEmail(list1,request.getMessage(),request.getPriority());
        return "Notification Sent!";
    }
}

class EmailRequest {
    private String message;  // The message to be sent
    private List<String> to; // List of email addresses to send the alert to

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    private String priority;  // The subject of the email

    // Getter for the message
    public String getMessage() {
        return message;
    }

    // Setter for the message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for the 'to' field (list of email addresses)
    public List<String> getTo() {
        return to;
    }

    // Setter for the 'to' field
    public void setTo(List<String> to) {
        this.to = to;
    }

    // Getter for the subject
//    public String getSubject() {
//        return subject;
//    }
//
//    // Setter for the subject
//    public void setSubject(String subject) {
//        this.subject = subject;
//    }
}