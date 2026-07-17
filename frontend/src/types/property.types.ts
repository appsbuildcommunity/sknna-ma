// ─────────────────────────────────────────────────────────────────
// property.types.ts
// Source : Contrat API Seknna v1.0 — Section 2 (Annonces — Groupe A + E)
// ─────────────────────────────────────────────────────────────────

/**
 * Types de logement acceptés par l'API.
 * Utilisés comme enum dans le modèle Property et les filtres de recherche.
 */
export type PropertyType =
  | 'student_room'
  | 'studio'
  | 'apartment'
  | 'shared_room'
  | 'house'

/**
 * Modèle Property complet — retourné par GET /api/properties/:id
 * et dans les listes paginées.
 * Chaque champ correspond exactement au tableau "Modèle Property" du contrat.
 */
export interface Property {
  id:            string        // UUID v4 — généré automatiquement
  title:         string
  description?:  string
  type:          PropertyType
  price:         number        // Prix mensuel en MAD (Dirham marocain)
  city:          string        // ex: "Casablanca"
  neighborhood?: string        // ex: "Maarif"
  latitude?:     number        // GPS — utilisé par Groupe C (map)
  longitude?:    number        // GPS — utilisé par Groupe C (map)
  availableFrom: string        // YYYY-MM-DD (ISO 8601)
  isAvailable:   boolean       // true = annonce active
  pictures?:     string[]      // Tableau d'URLs
  tags?:         string[]      // ex: ["wifi", "meublé", "proche-fac", "parking"]
  landlordId:    string        // UUID — défini automatiquement par le serveur
  createdAt:     string        // ISO 8601
}

/**
 * Corps de POST /api/properties (landlord uniquement)
 */
export interface CreatePropertyRequest {
  title:         string
  description?:  string
  type:          PropertyType
  price:         number
  city:          string
  neighborhood?: string
  latitude?:     number
  longitude?:    number
  availableFrom: string        // YYYY-MM-DD
  tags?:         string[]
}

/**
 * Corps de PUT /api/properties/:id
 * Tous les champs sont optionnels (mise à jour partielle)
 */
export type UpdatePropertyRequest = Partial<CreatePropertyRequest>

/**
 * Paramètres de GET /api/properties/search (Groupe E)
 * Tous optionnels et combinables.
 */
export interface PropertySearchParams {
  budgetMin?:     number
  budgetMax?:     number
  type?:          PropertyType
  city?:          string
  neighborhood?:  string
  availableFrom?: string       // YYYY-MM-DD
  lat?:           number       // latitude GPS pour filtre de proximité
  lng?:           number       // longitude GPS
  radius?:        number       // rayon en km (défaut: 5)
  tags?:          string       // virgule-séparés : "wifi,meublé"
  sortBy?:        'price_asc' | 'price_desc' | 'date_desc' | 'distance'
  page?:          number       // défaut: 1
  limit?:         number       // défaut: 10, max: 50
}

/**
 * Réponse paginée de GET /api/properties/search et GET /api/properties
 */
export interface PaginatedProperties {
  data:       Property[]
  pagination: Pagination
  filters?:   Partial<PropertySearchParams>
}

export interface Pagination {
  total:       number
  page:        number
  limit:       number
  totalPages:  number
}

/**
 * CustomerAnnouncement — POST /api/announcements/customer
 * Le tenant publie une demande de profil locataire (logique inversée)
 */
export interface CustomerAnnouncement {
  id:           string
  tenantId:     string
  city:         string
  budgetMin?:   number
  budgetMax?:   number
  propertyType?: PropertyType
  description?: string
  createdAt:    string
}
