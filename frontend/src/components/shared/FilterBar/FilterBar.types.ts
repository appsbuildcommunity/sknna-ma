import type {PropertyType} from '../../../types/property.types'

export interface FilterValues {
  city?:          string
  neighborhood?:  string
  budgetMin?:     number
  budgetMax?:     number
  type?:          PropertyType | ''
  availableFrom?: string
  tags?:          string
  sortBy?:        'price_asc' | 'price_desc' | 'date_desc' | 'distance' | ''
}

export interface FilterBarProps {
  onSearch:       (filters: FilterValues) => void
  onReset?:       () => void
  initialValues?: FilterValues
  isLoading?:     boolean
  variant?:       'bar' | 'sidebar'
}
