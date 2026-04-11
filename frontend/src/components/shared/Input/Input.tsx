import './Input.css'
import { forwardRef } from 'react'
import type {InputProps} from './Input.types'

export const Input = forwardRef<HTMLInputElement, InputProps>(function Input(
  { label, error, hint, leftIcon, rightIcon, fullWidth = false, className = '', id, ...props },
  ref
) {
  const inputId = id ?? label?.toLowerCase().replace(/\s+/g, '-')

  return (
    <div className={['sk-field', fullWidth ? 'sk-field-full' : ''].filter(Boolean).join(' ')}>
      {label && (
        <label htmlFor={inputId} className="sk-label">
          {label}
          {props.required && <span className="sk-required" aria-hidden>*</span>}
        </label>
      )}

      <div className="sk-input-wrap">
        {leftIcon  && <span className="sk-icon sk-icon-left">{leftIcon}</span>}

        <input
          ref={ref}
          id={inputId}
          className={[
            'sk-input',
            leftIcon  ? 'sk-input-has-left'  : '',
            rightIcon ? 'sk-input-has-right' : '',
            error     ? 'sk-input-error'     : '',
            className,
          ].filter(Boolean).join(' ')}
          aria-invalid={!!error}
          aria-describedby={
            error ? `${inputId}-error` : hint ? `${inputId}-hint` : undefined
          }
          {...props}
        />

        {rightIcon && <span className="sk-icon sk-icon-right">{rightIcon}</span>}
      </div>

      {error && (
        <span id={`${inputId}-error`} className="sk-error" role="alert">{error}</span>
      )}
      {!error && hint && (
        <span id={`${inputId}-hint`} className="sk-hint">{hint}</span>
      )}
    </div>
  )
})
