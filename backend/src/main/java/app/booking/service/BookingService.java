package app.booking.service;

import app.booking.dto.BookingMapper;
import app.booking.dto.BookingRequest;
import app.booking.dto.BookingResponse;
import app.booking.model.Booking;
import app.booking.model.BookingStatus;
import app.booking.repository.BookingRepository;
import app.property.model.Property;
import app.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyRepository propertyRepository;
    private final BookingMapper bookingMapper;

    // ─── POST /api/bookings ───────────────────────────────────────
    public BookingResponse createBooking(BookingRequest request, UUID tenantId) {

        // 1. Vérifier que la property existe
        Property property = propertyRepository.findById(request.propertyId())
                .orElseThrow(() -> new RuntimeException("Annonce introuvable"));

        // 2. Vérifier que la property est disponible
        if (!property.getIsAvailable()) {
            throw new RuntimeException("Cette annonce n'est plus disponible");
        }

        // 3. Vérifier qu'il n'y a pas déjà un booking pending
        boolean alreadyExists = bookingRepository.existsByPropertyIdAndTenantIdAndStatus(
                request.propertyId(), tenantId, BookingStatus.pending
        );
        if (alreadyExists) {
            throw new RuntimeException("Vous avez déjà une demande en cours pour cette annonce");
        }

        // 4. Créer le booking
        Booking booking = Booking.builder()
                .property(property)
                .tenantId(tenantId)
                .landlordId(property.getLandlordId())
                .message(request.message())
                .status(BookingStatus.pending)
                .build();

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponse(saved);
    }

    // ─── GET /api/bookings/my ─────────────────────────────────────
    public List<BookingResponse> getMyBookings(UUID tenantId, BookingStatus status) {

        List<Booking> bookings;

        if (status != null) {
            // filtrer par statut si fourni en query param
            bookings = bookingRepository.findByTenantIdAndStatus(tenantId, status);
        } else {
            bookings = bookingRepository.findByTenantId(tenantId);
        }

        return bookings.stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    // ─── DELETE /api/bookings/:id ─────────────────────────────────
    public void cancelBooking(UUID bookingId, UUID tenantId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));

        // Vérifier que c'est bien le tenant qui annule sa propre demande
        if (!booking.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Accès refusé");
        }

        // On peut annuler seulement si encore pending
        if (booking.getStatus() != BookingStatus.pending) {
            throw new RuntimeException("Impossible d'annuler une demande déjà traitée");
        }

        booking.setStatus(BookingStatus.cancelled);
        bookingRepository.save(booking);
    }
}