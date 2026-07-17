import './Button.css'
import type {ButtonProps} from './Button.types'
import { Loader } from '../Loader/Loader'

export function Button({
  variant   = 'primary',
  size      = 'md',
  isLoading = false,
  fullWidth = false,
  leftIcon,
  rightIcon,
  children,
  disabled,
  className = '',
  ...props
}: ButtonProps) {
  const isDisabled = disabled || isLoading

  const classes = [
    'btn',
    `btn-${variant}`,
    `btn-${size}`,
    fullWidth  ? 'btn-full' : '',
    className,
  ].filter(Boolean).join(' ')

  return (
    <button className={classes} disabled={isDisabled} aria-busy={isLoading} {...props}>
      {isLoading ? (
        <Loader size="sm" color={variant === 'primary' || variant === 'danger' ? 'white' : 'primary'} />
      ) : (
        <>
          {leftIcon  && <span className="btn-icon">{leftIcon}</span>}
          {children}
          {rightIcon && <span className="btn-icon">{rightIcon}</span>}
        </>
      )}
    </button>
  )
}
