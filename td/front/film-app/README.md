# FilmApp

Front Angular de gestion de films et d'acteurs (CRUD, et liaison acteur / film).

## Prérequis
Il faut avoir préalablement :
- Node.js et npm
- Java 26 min et une base PostgreSQL `proj-fullstack` sur `localhost:5432` avec les identifiants dans la config, ou modifier la config pour faire correspondre à la base postegre

## Lancer l'application

Etape 1. Démarrer le backend (port 8080), depuis `td/back` :

```bash
./gradlew bootRun
```

Etape  2. Démarrer le front, depuis `td/front/film-app` :

```bash
npm install
npm start
```

Ouvrir `http://localhost:4200/` sur le navigateur.
Les appels `/api` sont redirigés vers `http://localhost:8080` par `proxy.conf.json`.

## Fonctionnalités

- Films : liste, détail, création, modification, suppression
- Acteurs : liste, détail, création, modification, suppression
- Lier / retirer un acteur d'un film, depuis la page du film ou celle de l'acteur
- Les erreurs de l'API (dont API éteinte) s'affichent en haut de la page

## Structure
back : 
- `*DTO` les record exposees à l'api
- `*Repository` les repositories
- `*Service` les services
- `*Controller` les controllers

front :
- `*.service.ts` : seuls fichiers qui appellent l'API (`HttpClient`)
- `film-list`, `film-detail`, `acteur-list`, `acteur-detail` : pages
- `film-form`, `acteur-form` : composants qu'on réutilise dans les pages (`input()` pour la valeur, `output()` pour l'envoi)


specificites 
- Java utilise Lombok pour simplifier les getter et les setter

endpoints : 

| chemin            |           reponse            |
|-------------------|:----------------------------:|
| /api/films        |   liste de tous les films    |
| /api/films?genre=? | liste films par genre |
| /api/films/id     | liste les details du film avec l'id specifie |
| /api/acteurs      | liste les acteurs |
| /api/acteurs/id   | liste les details de l'acteur avec l'id specifie |


