package app.booking.repository;

import app.booking.model.Booking;
import app.booking.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    // GET /api/bookings/my → toutes les demandes d'un tenant
    List<Booking> findByTenantId(UUID tenantId);

    // GET /api/bookings/my?status=pending → filtrer par statut
    List<Booking> findByTenantIdAndStatus(UUID tenantId, BookingStatus status);

    // Vérifier si un booking pending existe déjà (éviter les doublons)
    boolean existsByPropertyIdAndTenantIdAndStatus(UUID propertyId, UUID tenantId, BookingStatus status);

    // GET /api/bookings/received → côté owner (P1 l'utilise aussi)
    List<Booking> findByLandlordId(UUID landlordId);
}