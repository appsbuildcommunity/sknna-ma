package app.notification.dto;

import app.notification.model.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class NotificationDto {

    private UUID id;
    private UUID recipientId;
    private NotificationType type;
    private String message;
    private UUID referenceId;
    private Boolean isRead;
    private LocalDateTime createdAt;
}