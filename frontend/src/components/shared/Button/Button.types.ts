import type {ButtonHTMLAttributes, ReactNode} from 'react'

export type ButtonVariant = 'primary' | 'secondary' | 'ghost' | 'danger'
export type ButtonSize    = 'sm' | 'md' | 'lg'

export interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  variant?:   ButtonVariant
  size?:      ButtonSize
  isLoading?: boolean
  fullWidth?: boolean
  leftIcon?:  ReactNode
  rightIcon?: ReactNode
  children:   ReactNode
}
