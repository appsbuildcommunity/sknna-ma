// ─────────────────────────────────────────────────────────────────
// booking.types.ts
// Source : Contrat API Seknna v1.0 — Section 3 (Demandes — Groupe A)
// ─────────────────────────────────────────────────────────────────

/**
 * États possibles d'une demande de location.
 * Seul le landlord peut passer à "accepted" ou "refused".
 * Seul le tenant peut passer à "cancelled".
 */
export type BookingStatus = 'pending' | 'accepted' | 'refused' | 'cancelled'

/**
 * Modèle Booking complet — retourné par POST /api/bookings
 * et dans les listes GET /api/bookings/my et /received.
 */
export interface Booking {
  id:              string         // UUID v4
  propertyId:      string         // UUID — annonce concernée
  tenantId:        string         // UUID — défini par le serveur via JWT
  landlordId:      string         // UUID — récupéré depuis la Property
  message?:        string         // Message optionnel du tenant
  status:          BookingStatus
  responseMessage?: string        // Message optionnel du landlord (accept/refuse)
  createdAt:       string         // ISO 8601
  updatedAt:       string         // ISO 8601
}

/**
 * Corps de POST /api/bookings (tenant uniquement)
 */
export interface CreateBookingRequest {
  propertyId: string
  message?:   string
}

/**
 * Corps de PATCH /api/bookings/:id/status (landlord uniquement)
 * Seules "accepted" et "refused" sont acceptées ici.
 */
export interface UpdateBookingStatusRequest {
  status:           'accepted' | 'refused'
  responseMessage?: string
}
