package app.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import app.notification.dto.NotificationDto;
import app.notification.model.NotificationType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationHelper {

    private final NotificationService notificationService;

    public NotificationDto notifyBookingRequestReceived(UUID ownerId, String propertyName,
                                                        String studentName, UUID bookingId) {
        String message = String.format("Nouvelle demande de location pour %s par %s", propertyName, studentName);
        return notificationService.createNotification(
                ownerId, NotificationType.BOOKING_REQUEST_RECEIVED,
                message, bookingId
        );
    }

    public NotificationDto notifyBookingAccepted(UUID studentId, String propertyName, UUID bookingId) {
        String message = String.format("Votre demande pour %s a été acceptée", propertyName);
        return notificationService.createNotification(
                studentId, NotificationType.BOOKING_ACCEPTED,
                message, bookingId
        );
    }

    public NotificationDto notifyBookingRefused(UUID studentId, String propertyName, UUID bookingId) {
        String message = String.format("Votre demande pour %s a été refusée", propertyName);
        return notificationService.createNotification(
                studentId, NotificationType.BOOKING_REFUSED,
                message, bookingId
        );
    }

    public NotificationDto notifyGroupInvitationReceived(UUID invitedUserId, String inviterName, UUID groupId) {
        String message = String.format("%s vous invite à rejoindre son groupe", inviterName);
        return notificationService.createNotification(
                invitedUserId, NotificationType.GROUP_INVITATION_RECEIVED,
                message, groupId
        );
    }

    public NotificationDto notifyGroupInvitationAccepted(UUID groupOwnerId, String acceptedName, UUID groupId) {
        String message = String.format("%s a accepté votre invitation", acceptedName);
        return notificationService.createNotification(
                groupOwnerId, NotificationType.GROUP_INVITATION_ACCEPTED,
                message, groupId
        );
    }

    public NotificationDto notifyGroupInvitationDeclined(UUID groupOwnerId, String declinedName, UUID groupId) {
        String message = String.format("%s a refusé votre invitation", declinedName);
        return notificationService.createNotification(
                groupOwnerId, NotificationType.GROUP_INVITATION_DECLINED,
                message, groupId
        );
    }

    public NotificationDto notifyGroupMemberJoined(UUID memberIdToNotify, String newMemberName, UUID groupId) {
        String message = String.format("%s a rejoint le groupe", newMemberName);
        return notificationService.createNotification(
                memberIdToNotify, NotificationType.GROUP_MEMBER_JOINED,
                message, groupId
        );
    }

    public NotificationDto notifyGroupMemberLeft(UUID memberIdToNotify, String leftMemberName, UUID groupId) {
        String message = String.format("%s a quitté le groupe", leftMemberName);
        return notificationService.createNotification(
                memberIdToNotify, NotificationType.GROUP_MEMBER_LEFT,
                message, groupId
        );
    }

    public NotificationDto notifyGroupJoinRequestReceived(UUID memberIdToNotify, String requesterName,
                                                          UUID requesterId, UUID groupId) {
        String message = String.format("%s souhaite rejoindre votre groupe", requesterName);
        return notificationService.createNotification(
                memberIdToNotify, NotificationType.GROUP_JOIN_REQUEST_RECEIVED,
                message, groupId
        );
    }

    public NotificationDto notifyGroupJoinRequestAccepted(UUID requesterId, String acceptorName, UUID groupId) {
        String message = String.format("%s a accepté votre demande, vous faites maintenant partie du groupe",
                acceptorName);
        return notificationService.createNotification(
                requesterId, NotificationType.GROUP_JOIN_REQUEST_ACCEPTED,
                message, groupId
        );
    }

    public NotificationDto notifyGroupJoinRequestRefused(UUID requesterId, String refuserName, UUID groupId) {
        String message = String.format("%s a refusé votre demande pour rejoindre le groupe", refuserName);
        return notificationService.createNotification(
                requesterId, NotificationType.GROUP_JOIN_REQUEST_REFUSED,
                message, groupId
        );
    }

    public NotificationDto notifyPropertyCreated(UUID adminId, String propertyTitle, UUID propertyId) {
        String message = String.format("Nouvelle annonce à vérifier : %s", propertyTitle);
        return notificationService.createNotification(
                adminId, NotificationType.PROPERTY_CREATED,
                message, propertyId
        );
    }

    public NotificationDto notifyPropertyReported(UUID adminId, UUID propertyId) {
        String message = String.format("Annonce %s signalée comme suspecte", propertyId);
        return notificationService.createNotification(
                adminId, NotificationType.PROPERTY_REPORTED,
                message, propertyId
        );
    }

    public NotificationDto notifyPropertyDisabled(UUID ownerId, UUID propertyId, String reason) {
        String message = String.format("Votre annonce a été désactivée (motif : %s)", reason);
        return notificationService.createNotification(
                ownerId, NotificationType.PROPERTY_DISABLED,
                message, propertyId
        );
    }

    public NotificationDto notifyPropertyVerified(UUID ownerId, UUID propertyId) {
        return notificationService.createNotification(
                ownerId, NotificationType.PROPERTY_VERIFIED,
                "Votre annonce a été vérifiée et publiée", propertyId
        );
    }

    public NotificationDto notifyAccountVerified(UUID userId) {
        return notificationService.createNotification(
                userId, NotificationType.ACCOUNT_VERIFIED,
                "Votre compte a été vérifié", userId
        );
    }

    public NotificationDto notifyAccountSuspended(UUID userId) {
        return notificationService.createNotification(
                userId, NotificationType.ACCOUNT_SUSPENDED,
                "Votre compte a été suspendu", userId
        );
    }

    public NotificationDto notifyAccountActivated(UUID userId) {
        return notificationService.createNotification(
                userId, NotificationType.ACCOUNT_ACTIVATED,
                "Votre compte a été réactivé", userId
        );
    }
}