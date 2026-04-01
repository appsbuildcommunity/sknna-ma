package app.user.tenant.dto;

import app.user.tenant.model.TenantType;
import java.time.LocalDateTime;
import java.util.UUID;

// record = classe immuable automatique
// pas besoin de getters/setters/constructeur
public record TenantResponse(

        // Infos de base (viennent de User)
        UUID id,
        String fullName,
        String email,
        String phone,

        // Infos spécifiques Tenant
        TenantType profileType,  // STUDENT ou YOUNG_PROFESSIONAL
        String bio,
        Boolean isVerified,

        // Dates
        LocalDateTime createdAt

        // ❌ PAS de password ici !
        // On ne renvoie JAMAIS le mot de passe au client
) {}