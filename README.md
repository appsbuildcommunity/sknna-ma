# Seknna

**A direct rental marketplace for Morocco — no intermediaries, no hidden fees.**

Seknna is an open-source web platform built by students of the AppsBuild community. It connects landlords and tenants directly, eliminating traditional real estate intermediaries ("semsars") and the commissions they charge. The platform targets students, interns, and young professionals seeking transparent and affordable housing across Morocco.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [Running with Docker](#running-with-docker)
- [Accessing the Services](#accessing-the-services)
- [Database Setup](#database-setup)
- [Contributing](#contributing)
- [Team](#team)

---

## Overview

Finding rental housing in Morocco is increasingly difficult for young people. Existing platforms are dominated by brokers who charge abusive commissions, and listings are often fake, outdated, or overpriced. Seknna solves this by providing a trusted, transparent marketplace with identity verification, digital contracts, and smart rent estimation.

The platform is currently in active development as a Sprint-based project with 9 contributors organized into cross-functional feature teams.

---

## Features

### Core MVP

- **Direct connection** between landlords and tenants with zero commission
- **Secure authentication** using JWT (access token and refresh token)
- **Interactive map** powered by OpenStreetMap and Leaflet.js, showing rental listings with price markers and clustering
- **Advanced search and filters** by budget, location, housing type, and availability
- **Booking system** allowing tenants to submit rental requests directly to owners
- **Group search** enabling multiple tenants to collaboratively search for shared housing (colocation)
- **Owner dashboard** to manage listings, view incoming requests, and accept or decline bookings
- **Tenant dashboard** to track submitted requests and their statuses in real time
- **Admin panel** for user moderation, listing management, and platform statistics
- **In-app notification system** for booking updates and group invitations

### Planned Features

- Identity verification for listings and property owners
- AI-powered housing recommendations and roommate matching
- 360-degree virtual tours and online visit booking
- Digital contract signing and secure payment integration
- Smart rent estimation based on neighborhood data and market trends

---

## Tech Stack

| Layer      | Technology                        |
|------------|-----------------------------------|
| Frontend   | React.js + Vite                   |
| Backend    | Spring Boot (Java)                |
| Database   | PostgreSQL                        |
| Auth       | JWT (Access Token + Refresh Token)|
| Map        | OpenStreetMap + Leaflet.js        |
| DevOps     | Docker + Docker Compose           |
| DB Admin   | pgAdmin 4                         |

---

## Project Structure

The repository follows a feature-based architecture. Each feature team owns its domain end-to-end (backend and frontend).

```
seknna/
├── frontend/               # React + Vite application
│   └── src/
│       ├── features/       # Feature modules (auth, properties, bookings, map, etc.)
│       │   └── {feature}/
│       │       ├── components/
│       │       ├── services/
│       │       └── hooks/
│       └── components/
│           └── shared/     # Shared UI kit (Button, Card, Modal, Navbar, etc.)
├── backend/                # Spring Boot application
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/) and Docker Compose
- [Node.js](https://nodejs.org/) (for local frontend development)
- Git

### Installation

1. Clone the repository:

```bash
git clone https://github.com/appsbuild/seknna.git
cd seknna
```

2. Create your local environment file:

```bash
cp .env.example .env
```

3. Edit `.env` with your local configuration (see [Environment Variables](#environment-variables)).

4. Install frontend dependencies:

```bash
cd frontend
npm install
```

---

## Environment Variables

The `.env` file is excluded from version control. Each developer must create their own local copy. Below is the expected structure:

```env
# Database
EMAIL=admin@admin.com
DB_NAME=sknna
DB_USER=sknna_user
DB_PASSWORD=secret123
DB_PORT=5432

# Backend
SPRING_PORT=8080

# Frontend
FRONTEND_PORT=5173
```

> Never commit your `.env` file. It is listed in `.gitignore` by default.

---

## Running with Docker

To start the full application stack (PostgreSQL, Spring Boot backend, React frontend, and pgAdmin):

```bash
docker compose up --build
```

To run containers in the background:

```bash
docker compose up -d
```

To stop all containers:

```bash
docker compose down
```

To stop and remove all volumes:

```bash
docker compose down -v
```

---

## Accessing the Services

Once the containers are running, the following services are available:

| Service     | URL                        |
|-------------|----------------------------|
| Frontend    | http://localhost:5173       |
| Backend API | http://localhost:8080       |
| pgAdmin     | http://localhost:5050       |

---

## Database Setup

Access pgAdmin at `http://localhost:5050` using:

```
Email:    admin@admin.com
Password: secret123
```

To connect to the PostgreSQL container, register a new server with the following settings:

```
Host:     db
Port:     5432
Username: sknna_user
Password: secret123
Database: sknna
```

---

## Contributing

Seknna is developed by members of the AppsBuild community using a feature team model. Each team is responsible for one domain end-to-end.

### Branch naming convention

```
feature/groupe-x-description
fix/groupe-x-description
```

### Commit format

```
feat: add property search endpoint
fix: correct JWT expiry handling
```

### Rules

- Never merge directly into `main` without a peer review.
- Merge your branch at least once per day to avoid divergence.
- Update the shared API contract document whenever an endpoint changes.
- A task is considered done only when it is committed, tested via Postman or equivalent, renders without console errors on the frontend, handles the main error case, and has been reviewed by at least one teammate.

---

## Team

Seknna is built by 9 students from **AppsBuild**, a student community where members brainstorm, collaborate, and build real-world projects together.

| Group   | Domain                                           | Members |
|---------|--------------------------------------------------|---------|
| Group A | Properties, Booking, Owner, Tenant, Group Search | [nouhaila0204](https://github.com/nouhaila0204), [Hafidamesad](https://github.com/Hafidamesad), [Youmna-Lahb04](https://github.com/Youmna-Lahb04) |
| Group B | Authentication, JWT, Security Guards             | [hassaneelhariti](https://github.com/hassaneelhariti)|
| Group C | Map (OpenStreetMap), Shared UI Kit               | [souad-a](https://github.com/souad-a) |
| Group D | Admin Dashboard, Notifications                   | [Elhoussine-07](https://github.com/Elhoussine-07), [ousam713](https://github.com/ousam713) |
| Group E | Search Filters, Search Engine                    | [hajar-B20](https://github.com/hajar-B20) |

---

## License

This project is developed for educational and community purposes under the AppsBuild initiative.
