// ─────────────────────────────────────────────────────────────────
// group.types.ts
// Source : Contrat API Seknna v1.0 — Section 4 (GroupSearch — Groupe A)
// Fonctionnalité unique Seknna : recherche collaborative de colocation
// ─────────────────────────────────────────────────────────────────

// ─────────────────────────────────────────────────────────────────
// group.types.ts
// Source : Contrat API Seknna v1.0 — Section 4 (GroupSearch — Groupe A)
// Fonctionnalité unique Seknna : recherche collaborative de colocation
// ─────────────────────────────────────────────────────────────────
import type {PropertyType} from './property.types'

/**
 * Statut d'un membre dans un groupe de recherche.
 */
export type GroupMemberStatus = 'pending' | 'member' | 'left'

/**
 * Membre d'un groupe — inclus dans GroupSearchProperties.members
 */
export interface GroupMember {
  userId:    string              // UUID
  name:      string
  status:    GroupMemberStatus
  joinedAt:  string              // ISO 8601
}

/**
 * Modèle GroupSearchProperties complet
 * Retourné par POST /api/groups et GET /api/groups/:id
 */
export interface GroupSearch {
  id:           string           // UUID v4
  name:         string           // ex: "Coloc Fac Médecine 2025"
  description?: string
  city:         string
  budgetMin?:   number           // Budget minimum partagé en MAD
  budgetMax?:   number           // Budget maximum partagé en MAD
  propertyType?: PropertyType
  creatorId:    string           // UUID — créateur automatiquement membre
  members:      GroupMember[]
  savedProperties?: string[]     // UUIDs des annonces sauvegardées par le groupe
  createdAt:    string           // ISO 8601
}

/**
 * Corps de POST /api/groups (tenant uniquement)
 */
export interface CreateGroupRequest {
  name:          string
  description?:  string
  city:          string
  budgetMin?:    number
  budgetMax?:    number
  propertyType?: PropertyType
}

/**
 * Corps de POST /api/groups/:id/join
 * inviteToken est optionnel — groupes publics dans le MVP
 */
export interface JoinGroupRequest {
  inviteToken?: string    // POST-MVP : pour les groupes privés
}

/**
 * Corps de POST /api/groups/:id/invite (POST-MVP)
 * Au moins un des deux champs est requis.
 */
export interface InviteGroupRequest {
  email?:  string
  userId?: string
}

/**
 * Corps de POST /api/groups/:id/properties
 * Sauvegarder une annonce dans le groupe pour discussion
 */
export interface SavePropertyToGroupRequest {
  propertyId: string
}
