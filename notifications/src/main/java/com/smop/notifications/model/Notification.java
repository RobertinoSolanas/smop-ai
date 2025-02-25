package com.smop.notifications.model;

import lombok.Data;

@Data
public class Notification {
    private String userId;
    private NotificationType type;
    private String message;
    private Object payload;
}

enum NotificationType {
    BOOKING_CONFIRMATION,
    TRIP_UPDATE,
    VEHICLE_LOCATION,
    PAYMENT_STATUS
}
