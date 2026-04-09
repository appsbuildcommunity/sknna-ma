import './PropertyCard.css'
import type {PropertyCardProps} from './PropertyCard.types'

const TYPE_LABELS: Record<string, string> = {
  student_room: 'Chambre étudiant',
  studio:       'Studio',
  apartment:    'Appartement',
  shared_room:  'Chambre partagée',
  house:        'Maison',
}

// Format prix MAD — fr-MA locale
function formatPrice(price: number): string {
  return new Intl.NumberFormat('fr-MA').format(price)
}

// Format date ISO → affichage local
function formatDate(iso: string): string {
  return new Date(iso).toLocaleDateString('fr-MA', {
    day: 'numeric', month: 'short', year: 'numeric',
  })
}

const PLACEHOLDER = 'data:image/svg+xml,%3Csvg xmlns="http://www.w3.org/2000/svg" width="400" height="240"%3E%3Crect width="400" height="240" fill="%23f2f4f8"/%3E%3Ctext x="200" y="125" text-anchor="middle" font-family="sans-serif" font-size="13" fill="%238a95b0"%3EPas de photo%3C/text%3E%3C/svg%3E'

export function PropertyCard({
  property,
  onClick,
  onBookmark,
  isBookmarked = false,
  variant      = 'grid',
}: PropertyCardProps) {
  const img        = property.pictures?.[0] ?? PLACEHOLDER
  const typeLabel  = TYPE_LABELS[property.type] ?? property.type
  const tags       = property.tags?.slice(0, 3) ?? []
  const clickable  = !!onClick

  // GRID variant (.gcard)
  if (variant === 'grid') {
    return (
      <article
        className={`sk-pcard-grid${clickable ? ' sk-pcard-clickable' : ''}`}
        onClick={onClick}
        aria-label={property.title}
      >
        {/* Image */}
        <div className="sk-pcard-grid-img">
          <img src={img} alt={property.title} loading="lazy" />
          {property.isAvailable && (
            <span className="sk-pcard-verified">✓ Disponible</span>
          )}
          {onBookmark && (
            <button
              className={`sk-pcard-bookmark${isBookmarked ? ' sk-pcard-bookmark-active' : ''}`}
              onClick={e => { e.stopPropagation(); onBookmark() }}
              aria-label={isBookmarked ? 'Retirer des favoris' : 'Ajouter aux favoris'}
            >
              {isBookmarked ? '❤️' : '♡'}
            </button>
          )}
        </div>

        {/* Body */}
        <div className="sk-pcard-grid-body">
          <div className="sk-pcard-grid-top">
            <div>
              <div className="sk-pcard-grid-title">{property.title}</div>
              <div className="sk-pcard-grid-type">{typeLabel}</div>
            </div>
            <div className="sk-pcard-price-wrap">
              <span className="sk-pcard-price-amount">
                {formatPrice(property.price)} MAD
              </span>
              <small className="sk-pcard-price-unit">/mois</small>
            </div>
          </div>

          <div className="sk-pcard-meta">
            <span>📍 {property.neighborhood ? `${property.neighborhood}, ` : ''}{property.city}</span>
            <span>📅 Dispo. {formatDate(property.availableFrom)}</span>
          </div>

          {tags.length > 0 && (
            <div className="sk-pcard-tags">
              {tags.map(t => <span key={t} className="sk-pcard-tag">{t}</span>)}
              {(property.tags?.length ?? 0) > 3 && (
                <span className="sk-pcard-tag">+{(property.tags?.length ?? 0) - 3}</span>
              )}
            </div>
          )}

          <button
            className="sk-pcard-cta"
            onClick={e => { e.stopPropagation(); onClick?.() }}
          >
            Contacter le propriétaire
          </button>
        </div>
      </article>
    )
  }

  //  LIST variant (.lcard)
  return (
    <article
      className={`sk-pcard-list${clickable ? ' sk-pcard-clickable' : ''}`}
      onClick={onClick}
      aria-label={property.title}
    >
      <div className="sk-pcard-list-img">
        <img src={img} alt={property.title} loading="lazy" />
        {property.isAvailable && <span className="sk-pcard-verified">✓ Vérifié</span>}
        {onBookmark && (
          <button
            className={`sk-pcard-bookmark${isBookmarked ? ' sk-pcard-bookmark-active' : ''}`}
            onClick={e => { e.stopPropagation(); onBookmark() }}
            aria-label={isBookmarked ? 'Retirer des favoris' : 'Ajouter aux favoris'}
          >
            {isBookmarked ? '❤️' : '♡'}
          </button>
        )}
      </div>

      <div className="sk-pcard-list-body">
        <div className="sk-pcard-list-title">{property.title}</div>
        <div className="sk-pcard-list-sub">{typeLabel} · {property.city}</div>
        <div className="sk-pcard-list-meta">
          {tags.map(t => <span key={t}>{t}</span>)}
          <span>📅 {formatDate(property.availableFrom)}</span>
        </div>
        <div className="sk-pcard-list-foot">
          <div className="sk-pcard-list-price">
            {formatPrice(property.price)} MAD<small>/mois</small>
          </div>
          <button
            className="sk-pcard-list-btn"
            onClick={e => { e.stopPropagation(); onClick?.() }}
          >
            Contacter
          </button>
        </div>
      </div>
    </article>
  )
}
