package app.booking.controller;

import app.booking.dto.BookingRequest;
import app.booking.dto.BookingResponse;
import app.booking.model.BookingStatus;
import app.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // ─── POST /api/bookings ───────────────────────────────────────
    // Envoyer une demande de location
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request,
            // TODO : remplacer par @AuthenticationPrincipal quand JWT Groupe B livré
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        BookingResponse response = bookingService.createBooking(request, tenantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ─── GET /api/bookings/my ─────────────────────────────────────
    // Mes demandes envoyées (avec filtre optionnel par statut)
    @GetMapping("/my")
    public ResponseEntity<List<BookingResponse>> getMyBookings(
            @RequestHeader("X-Tenant-Id") UUID tenantId,
            @RequestParam(required = false) BookingStatus status
    ) {
        List<BookingResponse> bookings = bookingService.getMyBookings(tenantId, status);
        return ResponseEntity.ok(bookings);
    }

    // ─── DELETE /api/bookings/:id ─────────────────────────────────
    // Annuler une demande (seulement si encore pending)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable UUID id,
            @RequestHeader("X-Tenant-Id") UUID tenantId
    ) {
        bookingService.cancelBooking(id, tenantId);
        return ResponseEntity.noContent().build(); // 204
    }
}
//## ⚠️ Note importante sur le JWT
//
//Pour l'instant on utilise `@RequestHeader("X-Tenant-Id")` comme **mock** en attendant le JWT du Groupe B. Pour tester dans Postman tu envoies juste :
//```
//Header: X-Tenant-Id = <uuid-du-tenant>
//```
//
//Quand le Groupe B livre le JWT, on remplace par `@AuthenticationPrincipal` — **une seule ligne à changer**.