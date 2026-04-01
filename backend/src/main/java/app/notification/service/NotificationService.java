package app.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.notification.dto.NotificationDto;
import app.notification.dto.NotificationRequest;
import app.notification.model.Notification;
import app.notification.model.NotificationType;
import app.notification.exception.ResourceNotFoundException;
import app.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationDto createNotification(UUID recipientId, NotificationType type,
                                              String message, UUID referenceId) {
        log.info("Création d'une notification [{}] pour recipientId={}", type, recipientId);

        Notification notification = Notification.builder()
                .recipientId(recipientId)
                .type(type)
                .message(message)
                .referenceId(referenceId)
                .isRead(false)
                .build();

        Notification saved = notificationRepository.save(notification);
        log.debug("Notification créée avec ID={}", saved.getId());
        return toDto(saved);
    }

    /**
     * Crée une notification à partir d'un objet NotificationRequest.
     * Méthode pratique pour les appels via l'API REST.
     */
    public NotificationDto createNotification(NotificationRequest request) {
        return createNotification(
                request.getRecipientId(),
                request.getType(),
                request.getMessage(),
                request.getReferenceId()
        );
    }

    // ═══════════════════════════════════════════════════════════════
    // LECTURE
    // ═══════════════════════════════════════════════════════════════

    /**
     * Récupère les notifications d'un utilisateur.
     *
     * @param recipientId ID de l'utilisateur
     * @param unreadOnly  Si true, retourne uniquement les notifications non lues
     * @return Liste des notifications
     */
    @Transactional(readOnly = true)
    public List<NotificationDto> getUserNotifications(UUID recipientId, boolean unreadOnly) {
        log.debug("Récupération des notifications pour recipientId={}, unreadOnly={}", recipientId, unreadOnly);

        List<Notification> notifications = unreadOnly
                ? notificationRepository.findByRecipientIdAndIsReadFalseOrderByCreatedAtDesc(recipientId)
                : notificationRepository.findByRecipientIdOrderByCreatedAtDesc(recipientId);

        return notifications.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère une notification par son ID.
     *
     * @param id ID de la notification
     * @return La notification trouvée
     * @throws ResourceNotFoundException si la notification n'existe pas
     */
    @Transactional(readOnly = true)
    public NotificationDto getNotificationById(UUID id) {
        log.debug("Recherche de la notification ID={}", id);

        return notificationRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", id));
    }

    /**
     * Compte les notifications non lues d'un utilisateur.
     *
     * @param recipientId ID de l'utilisateur
     * @return Nombre de notifications non lues
     */
    @Transactional(readOnly = true)
    public long countUnread(UUID recipientId) {
        return notificationRepository.countByRecipientIdAndIsReadFalse(recipientId);
    }

    // ═══════════════════════════════════════════════════════════════
    // MISE À JOUR
    // ═══════════════════════════════════════════════════════════════

    /**
     * Marque une notification comme lue.
     *
     * @param id ID de la notification
     * @return La notification mise à jour
     * @throws ResourceNotFoundException si la notification n'existe pas
     */
    public NotificationDto markAsRead(UUID id) {
        log.info("Marquage comme lu de la notification ID={}", id);

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", id));

        notification.setIsRead(true);
        Notification updated = notificationRepository.save(notification);
        return toDto(updated);
    }

    /**
     * Marque toutes les notifications d'un utilisateur comme lues.
     *
     * @param recipientId ID de l'utilisateur
     * @return Nombre de notifications mises à jour
     */
    public int markAllAsRead(UUID recipientId) {
        log.info("Marquage de toutes les notifications comme lues pour recipientId={}", recipientId);
        int count = notificationRepository.markAllAsReadByRecipientId(recipientId);
        log.debug("{} notification(s) marquée(s) comme lue(s) pour recipientId={}", count, recipientId);
        return count;
    }

    // ═══════════════════════════════════════════════════════════════
    // SUPPRESSION
    // ═══════════════════════════════════════════════════════════════

    /**
     * Supprime une notification par son ID.
     *
     * @param id ID de la notification à supprimer
     * @throws ResourceNotFoundException si la notification n'existe pas
     */
    public void deleteNotification(UUID id) {
        log.info("Suppression de la notification ID={}", id);

        if (!notificationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Notification", id);
        }
        notificationRepository.deleteById(id);
    }

    /**
     * Supprime toutes les notifications d'un utilisateur.
     * À utiliser lors de la suppression d'un compte utilisateur.
     *
     * @param recipientId ID de l'utilisateur
     */
    public void deleteAllForUser(UUID recipientId) {
        log.info("Suppression de toutes les notifications pour recipientId={}", recipientId);
        notificationRepository.deleteAllByRecipientId(recipientId);
    }

    // ═══════════════════════════════════════════════════════════════
    // MAPPING PRIVÉ
    // ═══════════════════════════════════════════════════════════════

    /**
     * Convertit une entité Notification en DTO NotificationDto.
     */
    private NotificationDto toDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .recipientId(notification.getRecipientId())
                .type(notification.getType())
                .message(notification.getMessage())
                .referenceId(notification.getReferenceId())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}