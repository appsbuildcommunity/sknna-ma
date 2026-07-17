import React, { useState } from 'react'
import '../styles/variables.css'
import { Button }       from '../components/shared/Button'
import { Input }        from '../components/shared/Input'
import { Select }       from '../components/shared/Select'
import { Badge }        from '../components/shared/Badge'
import { Card }         from '../components/shared/Card'
import { Loader }       from '../components/shared/Loader'
import { Modal }        from '../components/shared/Modal'
import { Navbar }       from '../components/shared/Navbar'
import { Sidebar }      from '../components/shared/Sidebar'
import { PropertyCard } from '../components/shared/PropertyCard'
import { FilterBar }    from '../components/shared/FilterBar'
import { UserAvatar }   from '../components/shared/UserAvatar'
import type { FilterValues } from '../components/shared/FilterBar/FilterBar.types'

const MOCK_PROPERTY_1 = {
    id: '1', title: 'Studio moderne proche université Hassan II',
    type: 'studio' as const, price: 2800, city: 'Casablanca',
    neighborhood: 'Maarif', availableFrom: '2025-03-01',
    isAvailable: true, landlordId: 'landlord-1',
    tags: ['wifi', 'meublé', 'parking'], pictures: [],
    createdAt: '2025-02-15T10:30:00Z',
}
const MOCK_PROPERTY_2 = {
    id: '2', title: 'Appartement familial 3 chambres — Agdal',
    type: 'apartment' as const, price: 5200, city: 'Rabat',
    neighborhood: 'Agdal', availableFrom: '2025-04-01',
    isAvailable: true, landlordId: 'landlord-2',
    tags: ['meublé', 'ascenseur', 'gardien'], pictures: [],
    createdAt: '2025-02-10T08:00:00Z',
}
const MOCK_PROPERTY_3 = {
    id: '3', title: 'Chambre partagée — Fès Médina',
    type: 'shared_room' as const, price: 1500, city: 'Fès',
    availableFrom: '2025-05-01', isAvailable: false,
    landlordId: 'landlord-3', tags: ['proche-fac'], pictures: [],
    createdAt: '2025-01-20T12:00:00Z',
}

const SIDEBAR_ITEMS = [
    { id: 'dashboard',  label: 'Dashboard',    icon: '📊' },
    { id: 'properties', label: 'Mes annonces', icon: '🏠', badge: 3 },
    { id: 'bookings',   label: 'Demandes',     icon: '📋', badge: 12 },
    { id: 'groups',     label: 'Groupes',      icon: '👥' },
    { id: 'notifs',     label: 'Notifications',icon: '🔔', badge: 5 },
]

function Section({ title, id, children }: { title: string; id?: string; children: React.ReactNode }) {
    return (
        <section id={id} style={{ marginBottom: 56 }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: 14, marginBottom: 24 }}>
                <div style={{ width: 4, height: 24, background: 'var(--O)', borderRadius: 2, flexShrink: 0 }} />
                <h2 style={{ fontFamily: 'var(--font-display)', fontSize: 22, fontWeight: 700, color: 'var(--N)', margin: 0 }}>
                    {title}
                </h2>
                <div style={{ flex: 1, height: 1, background: 'var(--Gm)' }} />
            </div>
            {children}
        </section>
    )
}

function Row({ children }: { children: React.ReactNode }) {
    return <div style={{ display: 'flex', flexWrap: 'wrap', gap: 12, alignItems: 'flex-start' }}>{children}</div>
}

function SubLabel({ children }: { children: React.ReactNode }) {
    return (
        <div style={{ fontSize: 10, fontWeight: 700, textTransform: 'uppercase', letterSpacing: '.8px', color: 'var(--Tl)', marginBottom: 8, fontFamily: 'var(--font-body)' }}>
            {children}
        </div>
    )
}

function Block({ label, children }: { label: string; children: React.ReactNode }) {
    return (
        <div style={{ marginBottom: 20 }}>
            <SubLabel>{label}</SubLabel>
            <Row>{children}</Row>
        </div>
    )
}

export function ComponentsDemo(): React.JSX.Element {
    const [modalOpen,    setModalOpen]    = useState(false)
    const [inputVal,     setInputVal]     = useState('')
    const [bookmarked,   setBookmarked]   = useState(false)
    const [sidebarActive,setSidebarActive]= useState('properties')
    const [filterResult, setFilterResult] = useState<FilterValues | null>(null)

    return (
        <div style={{ background: 'var(--BG)', minHeight: '100vh', fontFamily: 'var(--font-body)' }}>

            {/*$$$$$$$$$$$ NAVBAR $$$$$$$$$$$ */}
            <Section title="Navbar" id="navbar">
                <div style={{ borderRadius: 'var(--r)', overflow: 'hidden', boxShadow: 'var(--s2)' }}>
                    <Navbar
                        links={[
                            { label: 'Accueil',   href: '#' },
                            { label: 'Locations', href: '#', active: true },
                            { label: 'Contact',   href: '#' },
                        ]}
                        onLogin={()   => alert('→ login')}
                        onPublish={()  => alert('→ publier')}
                    />
                </div>
                <div style={{ marginTop: 12, borderRadius: 'var(--r)', overflow: 'hidden', boxShadow: 'var(--s2)' }}>
                    <Navbar
                        links={[{ label: 'Dashboard', href: '#', active: true }]}
                        user={{ name: 'Youssef Bennani', role: 'landlord' }}
                        onLogout={() => alert('→ POST /api/auth/logout')}
                    />
                </div>
            </Section>

            <div style={{ maxWidth: 1100, margin: '0 auto', padding: '0 48px 80px' }}>


                {/* $$$$$$$$$$$ BUTTON $$$$$$$$$$$ */}
                <Section title="Button" id="button">
                    <Block label="Variantes">
                        <Button variant="primary">Primary</Button>
                        <Button variant="secondary">Secondary</Button>
                        <Button variant="ghost">Ghost</Button>
                        <Button variant="danger">Danger</Button>
                    </Block>
                    <Block label="Tailles">
                        <Button variant="primary" size="sm">Petit (sm)</Button>
                        <Button variant="primary" size="md">Moyen (md)</Button>
                        <Button variant="primary" size="lg">Grand (lg)</Button>
                    </Block>
                    <Block label="États">
                        <Button variant="primary" isLoading>Chargement…</Button>
                        <Button variant="primary" disabled>Désactivé</Button>
                        <Button variant="secondary" leftIcon={<span>📩</span>}>Avec icône gauche</Button>
                        <Button variant="primary"  rightIcon={<span>→</span>}>Avec icône droite</Button>
                    </Block>
                    <Block label="Pleine largeur">
                        <div style={{ width: '100%' }}>
                            <Button variant="primary" fullWidth>Bouton pleine largeur</Button>
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ BADGE $$$$$$$$$$$ */}
                <Section title="Badge" id="badge">
                    <Block label="Variantes">
                        <Badge variant="default">Default</Badge>
                        <Badge variant="navy">Navy</Badge>
                        <Badge variant="orange">Orange</Badge>
                        <Badge variant="success">Success</Badge>
                        <Badge variant="warning">Warning</Badge>
                        <Badge variant="danger">Danger</Badge>
                    </Block>
                    <Block label="Avec point (dot) — statuts booking">
                        <Badge variant="warning" dot>En attente</Badge>
                        <Badge variant="success" dot>Acceptée</Badge>
                        <Badge variant="danger"  dot>Refusée</Badge>
                        <Badge variant="default" dot>Annulée</Badge>
                    </Block>
                    <Block label="Tailles">
                        <Badge variant="navy" size="sm">Small</Badge>
                        <Badge variant="navy" size="md">Medium</Badge>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ LOADER $$$$$$$$$$$ */}
                <Section title="Loader" id="loader">
                    <Block label="Tailles (fond clair)">
                        <Loader size="sm" color="primary" />
                        <Loader size="md" color="primary" />
                        <Loader size="lg" color="primary" />
                    </Block>
                    <Block label="Sur fond sombre (navbar / bouton)">
                        <div style={{ background: 'var(--N)', borderRadius: 8, padding: '14px 20px', display: 'flex', gap: 20, alignItems: 'center' }}>
                            <Loader size="sm" color="white" />
                            <Loader size="md" color="white" />
                            <Loader size="lg" color="white" />
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ INPUT $$$$$$$$$$$ */}
                <Section title="Input" id="input">
                    <Block label="États">
                        <div style={{ width: 270 }}>
                            <Input label="Nom complet" placeholder="Souad AG" required fullWidth />
                        </div>
                        <div style={{ width: 270 }}>
                            <Input label="Email" type="email" placeholder="souad@example.com"
                                   value={inputVal} onChange={e => setInputVal(e.target.value)} fullWidth />
                        </div>
                        <div style={{ width: 270 }}>
                            <Input label="Mot de passe" type="password" placeholder="Min. 8 caractères" fullWidth />
                        </div>
                    </Block>
                    <Block label="Avec erreur / hint / icône">
                        <div style={{ width: 270 }}>
                            <Input label="Email invalide" value="email-invalide" error="Format email invalide" fullWidth />
                        </div>
                        <div style={{ width: 270 }}>
                            <Input label="Budget (MAD)" type="number" placeholder="1000" hint="Montant mensuel en Dirham" fullWidth />
                        </div>
                        <div style={{ width: 270 }}>
                            <Input label="Recherche" placeholder="Casablanca, Maarif…" leftIcon={<span style={{ fontSize: 14 }}>🔍</span>} fullWidth />
                        </div>
                    </Block>
                    <Block label="Désactivé">
                        <div style={{ width: 270 }}>
                            <Input label="Champ désactivé" value="Non modifiable" disabled fullWidth />
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ SELECT $$$$$$$$$$$ */}
                <Section title="Select" id="select">
                    <Block label="Exemples">
                        <div style={{ width: 250 }}>
                            <Select label="Type de logement" placeholder="Choisir…"
                                    options={[
                                        { value: 'student_room', label: 'Chambre étudiant' },
                                        { value: 'studio',       label: 'Studio' },
                                        { value: 'apartment',    label: 'Appartement' },
                                        { value: 'shared_room',  label: 'Chambre partagée' },
                                        { value: 'house',        label: 'Maison' },
                                    ]} fullWidth />
                        </div>
                        <div style={{ width: 250 }}>
                            <Select label="Ville"
                                    options={[
                                        { value: 'Casablanca', label: 'Casablanca' },
                                        { value: 'Rabat',      label: 'Rabat' },
                                        { value: 'Agadir',     label: 'Agadir' },
                                        { value: 'Marrakech',  label: 'Marrakech' },
                                        { value: 'Fès',        label: 'Fès' },
                                    ]} fullWidth />
                        </div>
                        <div style={{ width: 250 }}>
                            <Select label="Trier par"
                                    options={[
                                        { value: 'date_desc',  label: 'Plus récentes' },
                                        { value: 'price_asc',  label: 'Prix croissant' },
                                        { value: 'price_desc', label: 'Prix décroissant' },
                                        { value: 'distance',   label: 'Distance' },
                                    ]} fullWidth />
                        </div>
                    </Block>
                    <Block label="Avec erreur">
                        <div style={{ width: 250 }}>
                            <Select label="Rôle" options={[{ value: '', label: '' }]} error="Ce champ est requis" fullWidth />
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ CARD $$$$$$$$$$$ */}
                <Section title="Card" id="card">
                    <Block label="Variantes">
                        <Card style={{ width: 260 }}>
                            <Card.Header><strong style={{ color: 'var(--N)' }}>Card standard (radius md)</strong></Card.Header>
                            Contenu libre — texte, composants, images…
                            <Card.Footer>
                                <Button variant="primary" size="sm">Valider</Button>
                                <Button variant="secondary" size="sm">Annuler</Button>
                            </Card.Footer>
                        </Card>
                        <Card radius="lg" style={{ width: 220 }} hoverable>
                            <strong style={{ color: 'var(--N)', fontFamily: 'var(--font-display)' }}>Hoverable</strong>
                            <p style={{ fontSize: 13, color: 'var(--Tm)', marginTop: 8 }}>Survole-moi — élévation au hover.</p>
                        </Card>
                        <Card radius="xl" shadow="s2" style={{ width: 220 }}>
                            <strong style={{ color: 'var(--N)' }}>Shadow s2</strong>
                            <p style={{ fontSize: 13, color: 'var(--Tm)', marginTop: 8 }}>Ombre prononcée pour éléments importants.</p>
                        </Card>
                        <Card padding="none" style={{ width: 200, overflow: 'hidden' }}>
                            <div style={{ background: 'var(--N)', padding: '20px 16px', textAlign: 'center' }}>
                                <strong style={{ color: 'white', fontFamily: 'var(--font-display)' }}>padding="none"</strong>
                            </div>
                            <div style={{ padding: 16 }}>Contenu avec padding manuel.</div>
                        </Card>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$. USER AVATAR $$$$$$$$$$$ */}
                <Section title="UserAvatar" id="useravatar">
                    <Block label="Tailles">
                        <div style={{ display: 'flex', gap: 16, alignItems: 'flex-end' }}>
                            {(['xs','sm','md','lg','xl'] as const).map(s => (
                                <div key={s} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 6 }}>
                                    <UserAvatar name="Souad AG" size={s} lightBg />
                                    <span style={{ fontSize: 10, color: 'var(--Tl)' }}>{s}</span>
                                </div>
                            ))}
                        </div>
                    </Block>
                    <Block label="Avec rôle (showRole)">
                        <UserAvatar name="Nouhaila AM"  size="md" role="landlord" showRole lightBg />
                        <UserAvatar name="Hajar BD" size="md" role="tenant"   showRole lightBg />
                        <UserAvatar name="Hafida MEA" size="md" role="admin"    showRole lightBg />
                    </Block>
                    <Block label="Sur fond sombre (dans Navbar)">
                        <div style={{ background: 'var(--N)', padding: '16px 20px', borderRadius: 12, display: 'inline-flex', gap: 20, alignItems: 'center' }}>
                            <UserAvatar name="Souad AM" size="md" darkBg />
                            <UserAvatar name="Youmna LA" size="md" role="landlord" showRole darkBg />
                        </div>
                    </Block>
                    <Block label="Couleurs déterministes (basées sur le nom)">
                        {['Lahoussine EL','Hassane EL','Oussama BE','Hassan AC','Abdessalam Ai'].map(n => (
                            <div key={n} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 4 }}>
                                <UserAvatar name={n} size="md" lightBg />
                                <span style={{ fontSize: 9, color: 'var(--Tl)', maxWidth: 60, textAlign: 'center' }}>{n.split(' ')[0]}</span>
                            </div>
                        ))}
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ MODAL $$$$$$$$$$$ */}
                <Section title="Modal" id="modal">
                    <Block label="Clique pour ouvrir">
                        <Button variant="primary" onClick={() => setModalOpen(true)}>Ouvrir la Modal</Button>
                    </Block>
                    <Modal
                        isOpen={modalOpen}
                        onClose={() => setModalOpen(false)}
                        title="Confirmer la demande de location"
                        size="md"
                        footer={
                            <>
                                <Button variant="secondary" onClick={() => setModalOpen(false)}>Annuler</Button>
                                <Button variant="primary"   onClick={() => { setModalOpen(false); alert('→ POST /api/bookings') }}>
                                    Envoyer la demande
                                </Button>
                            </>
                        }
                    >
                        <p style={{ marginBottom: 16 }}>
                            Voulez-vous envoyer une demande pour ce logement ?
                            Le propriétaire recevra une notification et pourra accepter ou refuser.
                        </p>
                        <Input label="Message (optionnel)" placeholder="Bonjour, je suis étudiant…" fullWidth />
                    </Modal>
                </Section>

                {/* $$$$$$$$$$$ SIDEBAR $$$$$$$$$$$ */}
                <Section title="Sidebar" id="sidebar">
                    <Block label="Mode navigation (avec items + badge)">
                        <div style={{ display: 'flex', gap: 20 }}>
                            <div style={{ border: '1px solid var(--Gm)', borderRadius: 'var(--r)', overflow: 'hidden', height: 320 }}>
                                <Sidebar
                                    title="Navigation"
                                    items={SIDEBAR_ITEMS}
                                    activeId={sidebarActive}
                                />
                            </div>
                            <div style={{ border: '1px solid var(--Gm)', borderRadius: 'var(--r)', overflow: 'hidden', height: 320 }}>
                                <Sidebar
                                    title="Navigation"
                                    items={SIDEBAR_ITEMS}
                                    activeId={sidebarActive}
                                    collapsed
                                />
                            </div>
                            <div style={{ display: 'flex', flexDirection: 'column', gap: 8, justifyContent: 'center' }}>
                                <p style={{ fontSize: 13, color: 'var(--Tm)' }}>Clique un item :</p>
                                {SIDEBAR_ITEMS.map(i => (
                                    <Button key={i.id} variant={sidebarActive === i.id ? 'primary' : 'secondary'}
                                            size="sm" onClick={() => setSidebarActive(i.id)}>
                                        {i.label}
                                    </Button>
                                ))}
                            </div>
                        </div>
                    </Block>
                    <Block label="Mode filtre (slot children libre — page recherche)">
                        <div style={{ width: 280, border: '1px solid var(--Gm)', borderRadius: 'var(--r)', overflow: 'hidden', background: 'white' }}>
                            <Sidebar title="🎛 Filtres" onReset={() => alert('reset')}>
                                <div style={{ padding: '0 0 16px' }}>
                                    <FilterBar variant="sidebar" onSearch={f => setFilterResult(f)} onReset={() => setFilterResult(null)} />
                                </div>
                            </Sidebar>
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ PAGE LAYOUT $$$$$$$$$$$ */}
                <Section title="PageLayout" id="pagelayout">
                    <Block label="Aperçu de la structure (miniature)">
                        <div style={{ width: '100%', border: '1px solid var(--Gm)', borderRadius: 'var(--r)', overflow: 'hidden', height: 240, transform: 'scale(1)', boxShadow: 'var(--s1)' }}>
                            <div style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
                                <div style={{ background: 'var(--N)', height: 36, display: 'flex', alignItems: 'center', padding: '0 16px', flexShrink: 0 }}>
                                    <span style={{ fontFamily: 'var(--font-display)', color: 'var(--C)', fontSize: 13 }}>Sknna</span>
                                    <span style={{ fontFamily: 'var(--font-display)', color: 'var(--O)', fontSize: 13 }}>.ma</span>
                                    <span style={{ marginLeft: 'auto', fontSize: 10, color: 'rgba(255,255,255,.5)' }}>← Navbar slot</span>
                                </div>
                                <div style={{ display: 'flex', flex: 1, overflow: 'hidden' }}>
                                    <div style={{ width: 100, background: 'white', borderRight: '1px solid var(--Gm)', padding: 8, flexShrink: 0 }}>
                                        <div style={{ fontSize: 9, color: 'var(--Tl)', marginBottom: 8 }}>← Sidebar slot</div>
                                        {['Dashboard','Annonces','Demandes'].map(l => (
                                            <div key={l} style={{ fontSize: 9, color: 'var(--Tm)', padding: '4px 6px', borderRadius: 4, marginBottom: 2, background: l === 'Annonces' ? 'var(--G)' : 'transparent' }}>{l}</div>
                                        ))}
                                    </div>
                                    <div style={{ flex: 1, padding: 16, background: 'var(--BG)', overflow: 'auto' }}>
                                        <div style={{ fontSize: 9, color: 'var(--Tl)', marginBottom: 8 }}>← Main content slot (children)</div>
                                        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr 1fr', gap: 6 }}>
                                            {[1,2,3].map(n => (
                                                <div key={n} style={{ background: 'white', borderRadius: 4, padding: 8, border: '1px solid var(--Gm)', fontSize: 8, color: 'var(--Tm)' }}>
                                                    Card {n}
                                                </div>
                                            ))}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div style={{ marginTop: 12, fontSize: 13, color: 'var(--Tm)', fontFamily: 'var(--font-body)' }}>
                            Usage : <code style={{ background: 'var(--G)', padding: '2px 8px', borderRadius: 4, fontSize: 12 }}>
                            {'<PageLayout navbar={<Navbar/>} sidebar={<Sidebar/>}>contenu</PageLayout>'}
                        </code>
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ PROPERTY CARD $$$$$$$$$$$ */}
                <Section title="PropertyCard" id="propertycard">
                    <Block label="Variante grid — 3 colonnes page recherche">
                        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 300px)', gap: 16 }}>
                            <PropertyCard property={MOCK_PROPERTY_1} variant="grid"
                                          onClick={() => alert('→ GET /api/properties/1')}
                                          onBookmark={() => setBookmarked(b => !b)}
                                          isBookmarked={bookmarked} />
                            <PropertyCard property={MOCK_PROPERTY_2} variant="grid"
                                          onClick={() => alert('→ GET /api/properties/2')} />
                            <PropertyCard property={MOCK_PROPERTY_3} variant="grid" />
                        </div>
                    </Block>
                    <Block label="Variante list — page accueil">
                        <div style={{ display: 'flex', flexDirection: 'column', gap: 10, width: 560 }}>
                            <PropertyCard property={MOCK_PROPERTY_1} variant="list" onClick={() => alert('clic list')} />
                            <PropertyCard property={MOCK_PROPERTY_2} variant="list" />
                        </div>
                    </Block>
                </Section>

                {/* $$$$$$$$$$$ FILTER BAR $$$$$$$$$$$ */}
                <Section title="FilterBar" id="filterbar">
                    <Block label="Variante bar — hero search (page accueil, sur fond sombre)">
                        <div style={{ background: 'var(--N)', padding: '32px 40px', borderRadius: 'var(--rl)', width: '100%' }}>
                            <FilterBar variant="bar" onSearch={f => setFilterResult(f)} />
                        </div>
                    </Block>
                    <Block label="Variante sidebar — page recherche + résultat live">
                        <div style={{ display: 'flex', gap: 20, alignItems: 'flex-start', flexWrap: 'wrap' }}>
                            <div style={{ width: 280, background: 'white', padding: 24, borderRadius: 'var(--rl)', border: '1px solid var(--Gm)', boxShadow: 'var(--s1)' }}>
                                <FilterBar variant="sidebar"
                                           onSearch={f  => setFilterResult(f)}
                                           onReset={() => setFilterResult(null)} />
                            </div>
                            <div style={{ flex: 1, minWidth: 280, background: 'var(--Nd)', borderRadius: 'var(--r)', padding: 20, minHeight: 120 }}>
                                <div style={{ fontSize: 10, fontWeight: 700, color: 'var(--Tl)', letterSpacing: '.6px', marginBottom: 12 }}>
                                    FILTRES ENVOYÉS → GET /api/properties/search
                                </div>
                                {filterResult
                                    ? <pre style={{ color: 'var(--C)', fontSize: 12, margin: 0, lineHeight: 1.6, whiteSpace: 'pre-wrap' }}>
                      {JSON.stringify(filterResult, null, 2)}
                    </pre>
                                    : <span style={{ color: 'var(--Tl)', fontSize: 13 }}>Lance une recherche pour voir les paramètres…</span>
                                }
                            </div>
                        </div>
                    </Block>
                </Section>

                {/* Footer */}
                <div style={{ borderTop: '1px solid var(--Gm)', paddingTop: 24, color: 'var(--Tl)', fontSize: 12, display: 'flex', justifyContent: 'space-between' }}>
                    <span>Seknna.ma · UI Kit v1.0 · Groupe C · Sprint 1</span>
                    <span>13 composants · CSS Variables · TypeScript</span>
                </div>

            </div>
        </div>
    )
}