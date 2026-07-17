import type {InputHTMLAttributes, ReactNode} from 'react'

export interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  label?:     string
  error?:     string
  hint?:      string
  leftIcon?:  ReactNode
  rightIcon?: ReactNode
  fullWidth?: boolean
}
