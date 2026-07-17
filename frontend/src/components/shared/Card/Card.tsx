import './Card.css'
import type {ReactNode} from 'react'
import type {CardProps} from './Card.types'

export function Card({
  children,
  padding   = 'md',
  radius    = 'md',
  hoverable = false,
  shadow    = 's1',
  className = '',
  ...props
}: CardProps) {
  const classes = [
    'sk-card',
    `sk-card-${radius}`,
    `sk-card-${shadow}`,
    `sk-card-p-${padding}`,
    hoverable ? 'sk-card-hoverable' : '',
    className,
  ].filter(Boolean).join(' ')

  return <div className={classes} {...props}>{children}</div>
}

Card.Header = function CardHeader({ children, className = '' }: { children: ReactNode; className?: string }) {
  return <div className={`sk-card-header ${className}`}>{children}</div>
}

Card.Footer = function CardFooter({ children, className = '' }: { children: ReactNode; className?: string }) {
  return <div className={`sk-card-footer ${className}`}>{children}</div>
}
