package com.example.Disaster.Management.Controllers;

import com.example.Disaster.Management.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-alert")
public class AlertController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public String sendAlert(@RequestBody EmailRequest request) {
        try {
            // log.info("Received alert request with subject: " + request.getSubject());
            System.out.println("emailsss"+request.getTo());
            emailService.sendAlertEmail(request.getTo(), request.getMessage(), request.getPriority());
            return "Alert Sent!";

        } catch (Exception e) {
            //   log.error("Error sending alert email: " + e.getMessage());
            System.out.println(e);
            return "Error: " + e.getMessage();
        }
    }
}