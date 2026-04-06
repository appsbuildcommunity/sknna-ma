package app.booking.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record BookingRequest(
//Records are ideal for DTOs because they automatically generate constructors,
// getters, equals(), hashCode(), and toString() methods,
// eliminating boilerplate code while making the intent clear
        @NotNull(message = "propertyId est obligatoire")
        UUID propertyId,

        // message optionnel selon le plan
        String message
) {}