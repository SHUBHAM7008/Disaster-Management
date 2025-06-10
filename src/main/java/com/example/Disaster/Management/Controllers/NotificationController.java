package com.example.Disaster.Management.Controllers;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noti")
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Endpoint to send notification messages to all users

    @MessageMapping("/sendNotification")  // This maps the incoming message to this method
    @SendTo("/topic/cricket")  // This broadcasts the message to the "/topic/cricket" topic
    public String sendNotification(String message) {
        System.out.println("Received message: " + message);  // Log the received message (for debugging)
        return message;  // Return the message to be broadcasted to subscribers
    }

    // Send notification to all users
    @GetMapping("/broadcastNotification")
    public String broadcastNotification(@RequestParam String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message +"Hi" );  // Broadcast to all users
        return "Notification sent!";
    }

}
