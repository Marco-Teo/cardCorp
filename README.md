CardCorp

CardCorp è un'applicazione full-stack per la gestione di carte collezionabili, sviluppata con Spring Boot (Java) nel backend e Next.js/React (TypeScript) nel frontend.

Caratteristiche principali

Catalogo carte con ricerca.

Autenticazione JWT per utenti e amministratori.

Gestione dei preferiti per utenti loggati.

Carrello e checkout con creazione ordini per utenti loggati.

CRUD carte riservato agli amministratori con la possibilita di aggiungere e rimuovere carte.

Struttura del progetto

cardcorp/
├─ backend/             # Applicazione Spring Boot
│   ├─ src/main/java   # Codice sorgente Java
│   ├─ src/main/resources
│   │   └─ application.properties
│   └─ pom.xml         # Dipendenze e plugin Maven
└─ frontend/            # Applicazione Next.js
    ├─ pages/          # Pagine React
    ├─ components/     # Componenti UI
    ├─ state/          # Redux slices
    └─ package.json    # Dipendenze Node.js
    
Configurazione

Clona la repository:

clona front-end 

git clone https://github.com/Marco-Teo/cardcorp.git

clona back-end 

git clone https://github.com/Marco-Teo/card-corp

Backend: crea un database PostgreSQL e configura le credenziali in backend/src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/cardcorp
spring.datasource.username=tuo_user
spring.datasource.password=tuo_password
jwt.secret=una-chiave-segreta-lunga-almeno-53-caratteri
jwt.duration=1800000

Frontend: copia .env.example in frontend/.env.local e imposta:

NEXT_PUBLIC_API_URL=http://localhost:8080

Avvio

Backend

cd backend
per avviare il back-end andare alla pagina CardcorpApplication e premere il triangolo verde in alto


Frontend

cd frontend
npm install
npm run dev


L'interfaccia sarà disponibile su http://localhost:3000.

API Endpoints

Autenticazione

POST /login – Login, restituisce JWT

POST /register – Registrazione utente

GET /utente – Ottieni profilo utente (header Authorization: Bearer <token>)

Carte (public)

GET /api/carte – Lista tutte le carte

POST /api/carte/search – Cerca carte con filtri

GET /api/carte/rarities – Elenco rarità

Carte (admin)

POST /api/carte – Crea nuova carta

DELETE /api/carte/{id} – Elimina carta

Preferiti (authenticated)

GET /users/{userId}/favorites – Lista preferiti\ n- POST /users/{userId}/favorites/{cartaId} – Aggiungi preferita

DELETE /users/{userId}/favorites/{cartaId} – Rimuovi preferita

Ordini (authenticated)

POST /ordini – Crea ordine

GET /ordini/user/{userId} – Elenco ordini utente (admin+user)

Ruoli e autorizzazioni

ADMIN: può creare, leggere, eliminare carte;

USER: può aggiungere/rimuovere preferiti, creare ordini;

ANONYMOUS: può solo visualizzare e cercare le carte (utilizzare il firltrio per le carte).

Contribuire

Fork del progetto

Crea un branch feature: git checkout -b feature/nome-feature

Commit delle modifiche: git commit -m "Aggiunta XYZ"

Push: git push origin feature/nome-feature

Apri una Pull Request


