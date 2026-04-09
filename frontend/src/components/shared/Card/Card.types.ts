import type {HTMLAttributes, ReactNode} from 'react'

export type CardPadding = 'none' | 'sm' | 'md' | 'lg'
export type CardRadius  = 'md' | 'lg' | 'xl'

export interface CardProps extends HTMLAttributes<HTMLDivElement> {
  children:   ReactNode
  padding?:   CardPadding
  radius?:    CardRadius
  hoverable?: boolean
  shadow?:    's1' | 's2' | 's3'
}
