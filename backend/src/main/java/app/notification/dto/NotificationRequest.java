package app.notification.dto;

import app.notification.model.NotificationType;
import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequest {

    private UUID recipientId;
    private NotificationType type;
    private String message;
    private UUID referenceId;
}