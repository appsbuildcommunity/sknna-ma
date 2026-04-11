import './Select.css'
import { forwardRef } from 'react'
import type {SelectProps} from './Select.types'

export const Select = forwardRef<HTMLSelectElement, SelectProps>(function Select(
  { label, options, error, hint, placeholder, fullWidth = false, className = '', id, ...props },
  ref
) {
  const selectId = id ?? label?.toLowerCase().replace(/\s+/g, '-')

  return (
    <div className={['sk-select-field', fullWidth ? 'sk-select-full' : ''].filter(Boolean).join(' ')}>
      {label && (
        <label htmlFor={selectId} className="sk-label">
          {label}
          {props.required && <span className="sk-required" aria-hidden>*</span>}
        </label>
      )}

      <div className="sk-select-wrap">
        <select
          ref={ref}
          id={selectId}
          className={['sk-select', error ? 'sk-select-error' : '', className].filter(Boolean).join(' ')}
          aria-invalid={!!error}
          {...props}
        >
          {placeholder && <option value="" disabled>{placeholder}</option>}
          {options.map(opt => (
            <option key={opt.value} value={opt.value} disabled={opt.disabled}>
              {opt.label}
            </option>
          ))}
        </select>

        <span className="sk-chevron" aria-hidden>
          <svg width="16" height="16" viewBox="0 0 16 16" fill="none">
            <path d="M4 6l4 4 4-4" stroke="currentColor" strokeWidth="1.5"
              strokeLinecap="round" strokeLinejoin="round"/>
          </svg>
        </span>
      </div>

      {error && <span className="sk-select-error-msg" role="alert">{error}</span>}
      {!error && hint && <span className="sk-select-hint">{hint}</span>}
    </div>
  )
})
