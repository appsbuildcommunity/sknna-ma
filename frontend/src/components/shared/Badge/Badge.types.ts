import type { ReactNode } from 'react'

export type BadgeVariant = 'default' | 'navy' | 'orange' | 'success' | 'warning' | 'danger'
export type BadgeSize    = 'sm' | 'md'

export interface BadgeProps {
  variant?:   BadgeVariant
  size?:      BadgeSize
  dot?:       boolean
  children:   ReactNode
  className?: string
}
