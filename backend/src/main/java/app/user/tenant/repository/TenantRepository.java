package app.user.tenant.repository;

import app.user.tenant.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    // Chercher un tenant par email
    // Utilisé pour : login, vérification doublon
    // SQL généré auto : SELECT * FROM tenants WHERE email = ?
    Optional<Tenant> findByEmail(String email);

    // Vérifier si un email existe déjà
    // Utilisé pour : inscription (éviter les doublons)
    // SQL généré auto : SELECT COUNT(*) FROM users WHERE email = ?
    boolean existsByEmail(String email);
}