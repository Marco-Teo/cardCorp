# CardCorp

CardCorp è un'applicazione full-stack per la gestione di carte collezionabili, sviluppata con Spring Boot (Java) nel backend e Next.js/React (TypeScript) nel frontend.

## Caratteristiche principali

- Catalogo carte con ricerca  
- Autenticazione JWT per utenti e amministratori  
- Gestione dei preferiti per utenti loggati  
- Carrello e checkout con creazione ordini per utenti loggati  
- CRUD carte riservato agli amministratori con la possibilità di aggiungere e rimuovere carte  

## Struttura del progetto

```plaintext
cardcorp/
├─ backend/                    # Applicazione Spring Boot
│  ├─ src/main/java/           # Codice sorgente Java
│  ├─ src/main/resources/      # Risorse (configurazioni, file statici)
│  │  └─ application.properties
│  └─ pom.xml                  # Dipendenze e plugin Maven
└─ frontend/                   # Applicazione Next.js
   ├─ pages/                   # Pagine React
   ├─ components/              # Componenti UI
   ├─ state/                   # Redux slices
   └─ package.json             # Dipendenze Node.js
```

## Configurazione

### Clonazione dei repository

```bash
# Frontend
git clone https://github.com/Marco-Teo/cardcorp.git

# Backend
git clone https://github.com/Marco-Teo/card-corp.git
```

### Backend

1. Crea un database PostgreSQL e configura le credenziali in `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/cardcorp
   spring.datasource.username=tuo_user
   spring.datasource.password=tuo_password
   jwt.secret=una-chiave-segreta-lunga-almeno-53-caratteri
   jwt.duration=1800000
   ```
2. Avvio:
   ```bash
   andare nella pagina CardcorpApplication e premere il triangolo verde in alto a destra.
   ```

### Frontend

1. Copia il file `.env.example` in `frontend/.env.local` e imposta:
   ```env
   NEXT_PUBLIC_API_URL=http://localhost:8080
   ```
2. Avvio:
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
L'interfaccia sarà disponibile su `http://localhost:3000`.

## API Endpoints

### Autenticazione
- `POST /login` – Login, restituisce JWT  
- `POST /register` – Registrazione utente  
- `GET /utente` – Ottieni profilo utente (header `Authorization: Bearer <token>`)

### Carte (public)
- `GET /api/carte` – Lista tutte le carte  
- `POST /api/carte/search` – Cerca carte con filtri  
- `GET /api/carte/rarities` – Elenco rarità  

### Carte (admin)
- `POST /api/carte` – Crea nuova carta  
- `DELETE /api/carte/{id}` – Elimina carta  

### Preferiti (authenticated)
- `GET /users/{userId}/favorites` – Lista preferiti  
- `POST /users/{userId}/favorites/{cartaId}` – Aggiungi preferita  
- `DELETE /users/{userId}/favorites/{cartaId}` – Rimuovi preferita  

### Ordini (authenticated)
- `POST /ordini` – Crea ordine  
- `GET /ordini/user/{userId}` – Elenco ordini utente (admin + user)

## Ruoli e autorizzazioni

- **ADMIN**: può creare, leggere, eliminare carte  
- **USER**: può aggiungere/rimuovere preferiti, creare ordini  
- **ANONYMOUS**: può solo visualizzare e cercare le carte  

## Contribuire

1. Fai fork del progetto  
2. Crea un branch feature:
   ```bash
   git checkout -b feature/nome-feature
   ```
3. Commit delle modifiche:
   ```bash
   git commit -m "Aggiunta XYZ"
   ```
4. Push e apri una Pull Request:
   ```bash
   git push origin feature/nome-feature
   ```
