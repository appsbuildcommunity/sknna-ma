import './Badge.css'
import type {BadgeProps} from './Badge.types'

export function Badge({
  variant   = 'default',
  size      = 'md',
  dot       = false,
  children,
  className = '',
}: BadgeProps) {
  const classes = [
    'sk-badge',
    `sk-badge-${variant}`,
    `sk-badge-${size}`,
    className,
  ].filter(Boolean).join(' ')

  return (
    <span className={classes}>
      {dot && <span className="sk-badge-dot" aria-hidden />}
      {children}
    </span>
  )
}
