import './FilterBar.css'
import { useState } from 'react'
import type {FilterBarProps, FilterValues} from './FilterBar.types'
import { Input }  from '../Input/Input'
import { Select } from '../Select/Select'
import { Button } from '../Button/Button'

const TYPE_CHIPS = [
  { value: '',             label: '🏠 Tous' },
  { value: 'student_room', label: '🎓 Étudiant' },
  { value: 'studio',       label: '🛋 Studio' },
  { value: 'apartment',    label: '🏢 Appartement' },
  { value: 'shared_room',  label: '🤝 Colocation' },
  { value: 'house',        label: '🏡 Villa' },
]

const SORT_OPTIONS = [
  { value: 'date_desc',  label: 'Plus récentes' },
  { value: 'price_asc',  label: 'Prix croissant' },
  { value: 'price_desc', label: 'Prix décroissant' },
  { value: 'distance',   label: 'Distance' },
]

const CITY_OPTIONS = [
  { value: '',           label: 'Toutes les villes' },
  { value: 'Casablanca', label: 'Casablanca' },
  { value: 'Rabat',      label: 'Rabat' },
  { value: 'Marrakech',  label: 'Marrakech' },
  { value: 'Agadir',     label: 'Agadir' },
  { value: 'Fès',        label: 'Fès' },
  { value: 'Tanger',     label: 'Tanger' },
]

const BUDGET_OPTIONS = [
  { value: '',     label: '1 000 – 3 000 MAD/mois' },
  { value: '3000', label: '3 000 – 5 000 MAD/mois' },
  { value: '5000', label: '5 000+ MAD/mois' },
]

const EMPTY: FilterValues = {
  city: '', neighborhood: '', budgetMin: undefined, budgetMax: undefined,
  type: '', availableFrom: '', tags: '', sortBy: 'date_desc',
}

function clean(f: FilterValues): FilterValues {
  const out: FilterValues = {}
  for (const [k, v] of Object.entries(f)) {
    if (v !== '' && v !== undefined) (out as Record<string, unknown>)[k] = v
  }
  return out
}

export function FilterBar({
  onSearch,
  onReset,
  initialValues = {},
  isLoading     = false,
  variant       = 'sidebar',
}: FilterBarProps) {
  const [f, setF] = useState<FilterValues>({ ...EMPTY, ...initialValues })

  function set(key: keyof FilterValues, value: string | number | undefined) {
    setF(prev => ({ ...prev, [key]: value }))
  }

  function handleSearch() { onSearch(clean(f)) }

  function handleReset() {
    setF(EMPTY)
    onReset?.()
  }

  // ── BAR variant — compact hero search ─────────────────────────────────
  if (variant === 'bar') {
    return (
      <div className="sk-filterbar-bar">
        <div className="sk-filterbar-bar-field">
          <span className="sk-filterbar-bar-label">Budget</span>
          <select
            className="sk-filterbar-bar-select"
            value={f.budgetMin ?? ''}
            onChange={e => set('budgetMin', e.target.value ? Number(e.target.value) : undefined)}
          >
            {BUDGET_OPTIONS.map(o => <option key={o.value} value={o.value}>{o.label}</option>)}
          </select>
        </div>

        <div className="sk-filterbar-bar-field">
          <span className="sk-filterbar-bar-label">Localisation</span>
          <select
            className="sk-filterbar-bar-select"
            value={f.city ?? ''}
            onChange={e => set('city', e.target.value)}
          >
            {CITY_OPTIONS.map(o => <option key={o.value} value={o.value}>{o.label}</option>)}
          </select>
        </div>

        <div className="sk-filterbar-bar-field">
          <span className="sk-filterbar-bar-label">Type de location</span>
          <select
            className="sk-filterbar-bar-select"
            value={f.type ?? ''}
            onChange={e => set('type', e.target.value as FilterValues['type'])}
          >
            {TYPE_CHIPS.map(o => <option key={o.value} value={o.value}>{o.label}</option>)}
          </select>
        </div>

        <button className="sk-filterbar-bar-btn" onClick={handleSearch} disabled={isLoading}>
          🔍 Rechercher
        </button>
      </div>
    )
  }

  // ── SIDEBAR variant — full filter panel ───────────────────────────────
  return (
    <div className="sk-filterbar-sidebar">

      {/* Type chips */}
      <div className="sk-filterbar-section-title">Type de logement</div>
      <div className="sk-filterbar-chips">
        {TYPE_CHIPS.map(chip => {
          const isActive = f.type === chip.value
          const activeClass = chip.value === ''
            ? 'sk-filterbar-chip-active-navy'
            : 'sk-filterbar-chip-active-orange'
          return (
            <button
              key={chip.value}
              className={`sk-filterbar-chip${isActive ? ` ${activeClass}` : ''}`}
              onClick={() => set('type', chip.value as FilterValues['type'])}
            >
              {chip.label}
            </button>
          )
        })}
      </div>

      {/* Ville */}
      <div className="sk-filterbar-section-title">Ville</div>
      <Select
        options={CITY_OPTIONS}
        value={f.city ?? ''}
        onChange={e => set('city', e.target.value)}
        fullWidth
      />

      {/* Quartier */}
      <div className="sk-filterbar-section-title">Quartier</div>
      <Input
        placeholder="Ex: Maarif, Guéliz…"
        value={f.neighborhood ?? ''}
        onChange={e => set('neighborhood', e.target.value)}
        fullWidth
      />

      {/* Budget */}
      <div className="sk-filterbar-section-title">Budget mensuel</div>
      <div className="sk-filterbar-budget-row">
        <Input
          type="number"
          placeholder="Min (MAD)"
          value={f.budgetMin ?? ''}
          onChange={e => set('budgetMin', e.target.value ? Number(e.target.value) : undefined)}
          fullWidth
        />
        <span className="sk-filterbar-budget-sep">–</span>
        <Input
          type="number"
          placeholder="Max (MAD)"
          value={f.budgetMax ?? ''}
          onChange={e => set('budgetMax', e.target.value ? Number(e.target.value) : undefined)}
          fullWidth
        />
      </div>

      {/* Date disponibilité */}
      <div className="sk-filterbar-section-title">Disponible à partir du</div>
      <Input
        type="date"
        value={f.availableFrom ?? ''}
        onChange={e => set('availableFrom', e.target.value)}
        fullWidth
      />

      {/* Tags */}
      <div className="sk-filterbar-section-title">Équipements</div>
      <Input
        placeholder="wifi, meublé, parking…"
        hint="Séparés par des virgules"
        value={f.tags ?? ''}
        onChange={e => set('tags', e.target.value)}
        fullWidth
      />

      {/* Tri */}
      <div className="sk-filterbar-section-title">Trier par</div>
      <div className="sk-filterbar-sort">
        <Select
          options={SORT_OPTIONS}
          value={f.sortBy ?? 'date_desc'}
          onChange={e => set('sortBy', e.target.value as FilterValues['sortBy'])}
          fullWidth
        />
      </div>

      {/* Actions */}
      <div className="sk-filterbar-actions">
        <Button variant="primary" onClick={handleSearch} isLoading={isLoading} fullWidth>
          Rechercher
        </Button>
        <Button variant="secondary" onClick={handleReset} size="md">
          Réinitialiser
        </Button>
      </div>

    </div>
  )
}
