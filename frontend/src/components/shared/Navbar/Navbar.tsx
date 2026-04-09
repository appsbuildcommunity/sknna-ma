import './Navbar.css'
import type {ReactNode} from 'react'

export interface NavLink {
  label:    string
  href:     string
  active?:  boolean
  onClick?: () => void
}

interface NavUser {
  name: string
  role: 'tenant' | 'landlord' | 'admin'
}

interface NavbarProps {
  links?:       NavLink[]
  user?:        NavUser | null
  onLogin?:     () => void
  onLogout?:    () => void
  onPublish?:   () => void
  rightExtra?:  ReactNode
}

export function Navbar({ links = [], user, onLogin, onLogout, onPublish, rightExtra }: NavbarProps) {
  const initials = user
    ? user.name.split(' ').map(n => n[0]).join('').toUpperCase().slice(0, 2)
    : ''

  return (
    <header className="sk-nav">
      {/* Logo */}
      <a href="/" className="sk-nav-logo">
        <span className="sk-nav-logo-s">Sknna</span>
        <span className="sk-nav-logo-m">.ma</span>
      </a>

      {/* Links */}
      {links.length > 0 && (
        <nav>
          <ul className="sk-nav-links">
            {links.map(link => (
              <li key={link.href}>
                <a
                  href={link.href}
                  className={`sk-nav-link${link.active ? ' sk-nav-link-active' : ''}`}
                  onClick={link.onClick ? e => { e.preventDefault(); link.onClick?.() } : undefined}
                >
                  {link.label}
                </a>
              </li>
            ))}
          </ul>
        </nav>
      )}

      {/* Actions */}
      <div className="sk-nav-actions">
        {rightExtra}

        {user ? (
          <div className="sk-nav-user">
            <span className="sk-nav-user-name">{user.name}</span>
            <button className="sk-nav-avatar" onClick={onLogout} title="Déconnexion">
              {initials}
            </button>
          </div>
        ) : (
          <>
            <button className="sk-nav-ghost" onClick={onLogin}>Se connecter</button>
            <button className="sk-nav-cta"   onClick={onPublish}>Publier une annonce</button>
          </>
        )}
      </div>
    </header>
  )
}
