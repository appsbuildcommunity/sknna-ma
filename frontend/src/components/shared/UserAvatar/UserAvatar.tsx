import './UserAvatar.css'

export type AvatarSize = 'xs' | 'sm' | 'md' | 'lg' | 'xl'

interface UserAvatarProps {
  name:        string
  size?:       AvatarSize
  imageUrl?:   string
  role?:       'tenant' | 'landlord' | 'admin'
  showRole?:   boolean
  darkBg?:     boolean   // true quand utilisé sur fond sombre (navbar)
  lightBg?:    boolean   // true quand utilisé sur fond blanc (cards)
  onClick?:    () => void
}

const ROLE_LABELS: Record<string, string> = {
  tenant:   'Locataire',
  landlord: 'Propriétaire',
  admin:    'Administrateur',
}

// Couleur déterministe basée sur le nom (0–4)
function colorIndex(name: string): number {
  return name.split('').reduce((acc, c) => acc + c.charCodeAt(0), 0) % 5
}

function initials(name: string): string {
  return name
    .trim()
    .split(/\s+/)
    .map(n => n[0])
    .join('')
    .toUpperCase()
    .slice(0, 2)
}

export function UserAvatar({
  name,
  size     = 'md',
  imageUrl,
  role,
  showRole = false,
  darkBg   = false,
  lightBg  = false,
  onClick,
}: UserAvatarProps) {
  const init = initials(name)
  const colorClass = `sk-avatar-c${colorIndex(name)}`
  const clickable  = !!onClick

  const avatarEl = (
    <span
      className={[
        'sk-avatar',
        `sk-avatar-${size}`,
        colorClass,
        lightBg  ? 'sk-avatar-light'    : '',
        clickable ? 'sk-avatar-clickable' : '',
      ].filter(Boolean).join(' ')}
      onClick={!showRole && onClick ? onClick : undefined}
      role={!showRole && onClick ? 'button' : undefined}
      tabIndex={!showRole && onClick ? 0 : undefined}
      aria-label={name}
    >
      {imageUrl
        ? <img src={imageUrl} alt={name} />
        : <span>{init}</span>
      }
    </span>
  )

  if (!showRole) return avatarEl

  return (
    <div
      className={[
        'sk-avatar-with-role',
        darkBg   ? 'sk-avatar-dark'      : '',
        clickable ? 'sk-avatar-clickable' : '',
      ].filter(Boolean).join(' ')}
      onClick={onClick}
      role={onClick ? 'button' : undefined}
      tabIndex={onClick ? 0 : undefined}
    >
      {avatarEl}
      <div className="sk-avatar-info">
        <span className="sk-avatar-name">{name}</span>
        {role && <span className="sk-avatar-role">{ROLE_LABELS[role] ?? role}</span>}
      </div>
    </div>
  )
}
