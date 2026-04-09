import type {SelectHTMLAttributes} from 'react'

export interface SelectOption {
  value:     string
  label:     string
  disabled?: boolean
}

export interface SelectProps extends SelectHTMLAttributes<HTMLSelectElement> {
  label?:       string
  options:      SelectOption[]
  error?:       string
  hint?:        string
  placeholder?: string
  fullWidth?:   boolean
}
