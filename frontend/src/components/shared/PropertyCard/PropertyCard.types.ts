// Réexporte depuis les types globaux pour éviter la duplication
export type { Property, PropertyType } from '../../../types/property.types'

export interface PropertyCardProps {
  property:      import('../../../types/property.types').Property
  onClick?:      () => void
  onBookmark?:   () => void
  isBookmarked?: boolean
  variant?:      'grid' | 'list'
}
