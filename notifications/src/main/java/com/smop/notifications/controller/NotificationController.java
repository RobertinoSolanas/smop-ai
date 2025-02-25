package com.smop.notifications.controller;

import com.smop.notifications.model.Notification;
import com.smop.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/notifications")
    public Notification sendNotification(Notification notification) {
        return notificationService.processNotification(notification);
    }

    @PostMapping
    public void sendNotificationViaRest(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
    }
}
