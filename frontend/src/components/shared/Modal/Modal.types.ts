import type {ReactNode} from 'react'

export type ModalSize = 'sm' | 'md' | 'lg'

export interface ModalProps {
  isOpen:           boolean
  onClose:          () => void
  title?:           string
  children:         ReactNode
  footer?:          ReactNode
  size?:            ModalSize
  closeOnOverlay?:  boolean
}
