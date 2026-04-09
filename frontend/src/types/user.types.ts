// ─────────────────────────────────────────────────────────────────
// user.types.ts
// Source : Contrat API Seknna v1.0 — Section 1 (Auth — Groupe B)
// Modèle retourné par GET /api/auth/me et POST /api/auth/login
// ─────────────────────────────────────────────────────────────────

/**
 * Rôles acceptés à l'inscription.
 * "admin" n'est jamais envoyé via /register — créé directement en base.
 */
export type UserRole = 'tenant' | 'landlord' | 'admin'

/**
 * Profil complet retourné par GET /api/auth/me
 */
export interface User {
  id:         string        // UUID v4
  name:       string
  email:      string
  role:       UserRole
  phone?:     string        // ex: "+212661234567"
  isVerified: boolean
  createdAt:  string        // ISO 8601 : "2025-02-15T10:30:00Z"
}

/**
 * Sous-ensemble retourné dans les réponses login / register
 * (sans isVerified ni phone)
 */
export interface AuthUser {
  id:    string
  name:  string
  email: string
  role:  UserRole
}

/**
 * Corps de POST /api/auth/register
 */
export interface RegisterRequest {
  name:     string   // min 2 caractères
  email:    string
  password: string   // min 8 caractères — hashé côté backend (bcrypt)
  role:     'tenant' | 'landlord'   // jamais "admin"
  phone?:   string
}

/**
 * Corps de POST /api/auth/login
 */
export interface LoginRequest {
  email:    string
  password: string
}

/**
 * Réponse de POST /api/auth/login et POST /api/auth/register
 * Le refreshToken est dans un cookie HttpOnly — jamais exposé ici.
 */
export interface AuthResponse {
  accessToken: string    // JWT valide 15 min — stocké en mémoire React (jamais localStorage)
  user:        AuthUser
}
