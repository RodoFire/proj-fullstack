import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FilmCreationDTO, FilmDTO, FilmDetailDTO } from './models/film.model';

@Injectable({
  providedIn: 'root'
})
export class FilmService {
  constructor(private httpClient: HttpClient) { }

  getAllFilms(): Observable<FilmDTO[]> {
    return this.httpClient.get<FilmDTO[]>('/api/films');
  }

  getFilm(id: number): Observable<FilmDetailDTO> {
    return this.httpClient.get<FilmDetailDTO>(`/api/films/${id}`);
  }

  createFilm(film: FilmCreationDTO): Observable<void> {
    return this.httpClient.post<void>('/api/films', film);
  }

  addActeur(filmId: number, acteurId: number): Observable<void> {
    return this.httpClient.post<void>(`/api/films/${filmId}/acteurs/${acteurId}`, null);
  }

  removeActeur(filmId: number, acteurId: number): Observable<void> {
    return this.httpClient.delete<void>(`/api/films/${filmId}/acteurs/${acteurId}`);
  }
}
