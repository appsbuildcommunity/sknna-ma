import './Modal.css'
import { useEffect, useRef } from 'react'
import { createPortal } from 'react-dom'
import type {ModalProps} from './Modal.types'

export function Modal({
  isOpen,
  onClose,
  title,
  children,
  footer,
  size           = 'md',
  closeOnOverlay = true,
}: ModalProps) {
  const ref = useRef<HTMLDivElement>(null)

  // Fermer avec Escape
  useEffect(() => {
    if (!isOpen) return
    const onKey = (e: KeyboardEvent) => { if (e.key === 'Escape') onClose() }
    document.addEventListener('keydown', onKey)
    return () => document.removeEventListener('keydown', onKey)
  }, [isOpen, onClose])

  // Bloquer le scroll body
  useEffect(() => {
    document.body.style.overflow = isOpen ? 'hidden' : ''
    return () => { document.body.style.overflow = '' }
  }, [isOpen])

  // Focus à l'ouverture
  useEffect(() => { if (isOpen) ref.current?.focus() }, [isOpen])

  if (!isOpen) return null

  return createPortal(
    <div
      className="sk-overlay"
      onClick={closeOnOverlay ? onClose : undefined}
      role="dialog"
      aria-modal="true"
      aria-labelledby={title ? 'sk-modal-title' : undefined}
    >
      <div
        ref={ref}
        className={`sk-dialog sk-dialog-${size}`}
        onClick={e => e.stopPropagation()}
        tabIndex={-1}
      >
        <div className="sk-dialog-header">
          {title
            ? <h2 id="sk-modal-title" className="sk-dialog-title">{title}</h2>
            : <span />
          }
          <button className="sk-dialog-close" onClick={onClose} aria-label="Fermer">
            <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
              <path d="M4 4l10 10M14 4L4 14" stroke="currentColor" strokeWidth="1.8"
                strokeLinecap="round"/>
            </svg>
          </button>
        </div>

        <div className="sk-dialog-body">{children}</div>

        {footer && <div className="sk-dialog-footer">{footer}</div>}
      </div>
    </div>,
    document.body
  )
}
