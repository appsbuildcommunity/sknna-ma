package app.user.tenant.dto;

import app.user.tenant.model.Tenant;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

    // Convertit Tenant (entité DB) → TenantResponse (JSON)
    // Appelé dans le Service avant de renvoyer au Controller
    public TenantResponse toResponse(Tenant tenant) {
        return new TenantResponse(
                tenant.getId(),
                tenant.getFullName(),
                tenant.getEmail(),
                tenant.getPhone(),
                tenant.getProfileType(),
                tenant.getBio(),
                tenant.getIsVerified(),
                tenant.getCreatedAt()
                // ← password non inclus volontairement ✅
        );
    }
}