import './Loader.css'

export type LoaderSize  = 'sm' | 'md' | 'lg'
export type LoaderColor = 'primary' | 'white' | 'gray'

interface LoaderProps {
  size?:  LoaderSize
  color?: LoaderColor
  label?: string
}

export function Loader({ size = 'md', color = 'primary', label = 'Chargement…' }: LoaderProps) {
  return (
    <span
      className={`sk-loader sk-loader-${size} sk-loader-${color}`}
      role="status"
      aria-label={label}
    >
      <span className="sk-sr">{label}</span>
    </span>
  )
}
