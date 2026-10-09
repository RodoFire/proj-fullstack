import { Routes } from '@angular/router';
import { FilmList } from './film-list/film-list';
import { FilmDetail } from './film-detail/film-detail';
import { ActeurList } from './acteur-list/acteur-list';
import { ActeurDetail } from './acteur-detail/acteur-detail';
import { BackDisconnected } from './back-disconnected/back-disconnected';

export const routes: Routes = [
  { path: '', redirectTo: 'films', pathMatch: 'full' },
  { path: 'films', component: FilmList },
  { path: 'films/:id', component: FilmDetail },
  { path: 'acteurs', component: ActeurList },
  { path: 'acteurs/:id', component: ActeurDetail },
  { path: 'back-disconnected', component: BackDisconnected },
];
