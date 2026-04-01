package app.notification.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.notification.dto.ApiResponse;
import app.notification.dto.NotificationDto;
import app.notification.dto.NotificationRequest;
import app.notification.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUserNotifications(
            @RequestParam UUID recipientId,
            @RequestParam(defaultValue = "false") boolean unreadOnly) {

        List<NotificationDto> notifications = notificationService.getUserNotifications(recipientId, unreadOnly);
        long unreadCount = notificationService.countUnread(recipientId);

        Map<String, Object> data = Map.of(
                "notifications", notifications,
                "total", notifications.size(),
                "unreadCount", unreadCount
        );

        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NotificationDto>> getById(@PathVariable UUID id) {
        NotificationDto notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(ApiResponse.success(notification));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<NotificationDto>> markAsRead(@PathVariable UUID id) {
        NotificationDto updated = notificationService.markAsRead(id);
        return ResponseEntity.ok(ApiResponse.success(updated));
    }

    @PutMapping("/read-all")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> markAllAsRead(@RequestParam UUID recipientId) {
        int updatedCount = notificationService.markAllAsRead(recipientId);
        return ResponseEntity.ok(ApiResponse.success(Map.of("updatedCount", updatedCount)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NotificationDto>> create(@RequestBody NotificationRequest request) {
        NotificationDto created = notificationService.createNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok(ApiResponse.success());
    }
}