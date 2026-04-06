package app.user.tenant.dto;

import app.user.tenant.model.TenantType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Ce que le client envoie pour modifier son profil
// PUT /tenants/me
public record TenantRequest(

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 100, message = "Nom trop long")
        String fullName,

        // Optionnel — pas de @NotBlank
        @Size(max = 20, message = "Téléphone trop long")
        String phone,

        // Optionnel
        String bio,

        // STUDENT ou YOUNG_PROFESSIONAL
        TenantType profileType

) {}