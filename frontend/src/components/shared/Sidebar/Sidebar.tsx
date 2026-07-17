import './Sidebar.css'
import type {ReactNode} from 'react'

export interface SidebarItem {
  id:       string
  label:    string
  icon?:    ReactNode
  badge?:   string | number
  href?:    string
  onClick?: () => void
}

interface SidebarProps {
  items?:       SidebarItem[]
  activeId?:    string
  title?:       string
  onReset?:     () => void
  header?:      ReactNode
  footer?:      ReactNode
  children?:    ReactNode   // slot libre pour contenu custom (ex: filtres)
  collapsed?:   boolean
}

export function Sidebar({
  items     = [],
  activeId,
  title,
  onReset,
  header,
  footer,
  children,
  collapsed = false,
}: SidebarProps) {
  return (
    <aside className={`sk-sidebar${collapsed ? ' sk-sidebar-collapsed' : ''}`}>
      {/* Header custom ou titre + reset */}
      {header ?? (title && (
        <div className="sk-sidebar-header">
          <span>{title}</span>
          {onReset && (
            <button className="sk-sidebar-reset" onClick={onReset}>Réinitialiser</button>
          )}
        </div>
      ))}

      {/* Nav items (si fournis) */}
      {items.length > 0 && (
        <nav className="sk-sidebar-nav" aria-label="Navigation">
          <ul className="sk-sidebar-list">
            {items.map(item => (
              <li key={item.id}>
                <a
                  href={item.href ?? '#'}
                  className={`sk-sidebar-item${activeId === item.id ? ' sk-sidebar-item-active' : ''}`}
                  onClick={item.onClick ? e => { e.preventDefault(); item.onClick?.() } : undefined}
                  aria-current={activeId === item.id ? 'page' : undefined}
                >
                  {item.icon && <span className="sk-sidebar-icon" aria-hidden>{item.icon}</span>}
                  {!collapsed && <span className="sk-sidebar-label">{item.label}</span>}
                  {!collapsed && item.badge !== undefined && (
                    <span className="sk-sidebar-badge">{item.badge}</span>
                  )}
                </a>
              </li>
            ))}
          </ul>
        </nav>
      )}

      {/* Slot libre (ex: filtres de recherche) */}
      {children}

      {footer && <div className="sk-sidebar-footer">{footer}</div>}
    </aside>
  )
}
