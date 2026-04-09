import './PageLayout.css'
import type {ReactNode} from 'react'

interface PageLayoutProps {
  children:   ReactNode
  navbar?:    ReactNode
  sidebar?:   ReactNode
  maxWidth?:  'sm' | 'md' | 'lg' | 'xl' | 'full'
  noPadding?: boolean
}

export function PageLayout({
  children,
  navbar,
  sidebar,
  maxWidth  = 'xl',
  noPadding = false,
}: PageLayoutProps) {
  return (
    <div className="sk-layout">
      {navbar && <div className="sk-layout-nav">{navbar}</div>}
      <div className="sk-layout-body">
        {sidebar && <div className="sk-layout-sidebar">{sidebar}</div>}
        <main className={`sk-layout-main${noPadding ? '' : ' sk-layout-main-padded'}`}>
          <div className={`sk-layout-content sk-layout-max-${maxWidth}`}>
            {children}
          </div>
        </main>
      </div>
    </div>
  )
}
