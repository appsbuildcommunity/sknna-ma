package app.booking.dto;

import app.booking.model.BookingStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponse(
        UUID id,
        UUID propertyId,
        String propertyTitle,
        String propertyCity,
        UUID tenantId,
        UUID landlordId,
        String message,
        String responseMessage,
        BookingStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}