package com.smop.notifications;

import com.smop.notifications.model.Notification;
import com.smop.notifications.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @Test
    void testSendNotification() {
        Notification notification = new Notification();
        notification.setUserId("user123");
        notification.setType(NotificationType.BOOKING_CONFIRMATION);
        notification.setMessage("Your booking is confirmed");

        notificationService.sendNotification(notification);

        verify(messagingTemplate).convertAndSend(
            eq("/topic/notifications"), 
            any(Notification.class));
    }
}
